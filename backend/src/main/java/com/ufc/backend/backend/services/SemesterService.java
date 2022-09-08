package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.*;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.utils.HandlePossibleClassesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterService {

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;

    /**
     * Return a {@link Semester} from the logged {@link User}
     *
     * @param index the index of the semesters
     * @return {@link Semester}
     */
    public Semester findSemesterByIndex(Integer index) {
        User user = userService.findById(AuthService.userAuthenticated().getId());

        return user.getSemester().stream().filter(obj -> obj.getIndex().equals(index)).
                findFirst().orElseThrow(SemesterOutOfBoundsException::new);
    }

    /**
     * Return a list containing all {@link Semester}
     *
     * @return a list of {@link Semester}
     */
    public List<Semester> findAll() {
        return userService.findById(AuthService.userAuthenticated().getId()).getSemester();
    }

    /**
     * Return a {@link Semester} from a given {@link User}
     *
     * @param user  the given user
     * @param index the index of the semester
     * @return {@link Semester}
     */
    public Semester findSemesterByIndex(User user, Integer index) {
        return user.getSemester().stream().filter(obj -> obj.getIndex().equals(index)).
                findFirst().orElseThrow(SemesterOutOfBoundsException::new);
    }

    /**
     * Delete a {@link Semester} by its index
     *
     * @param index the index of the semesters that should be deleted
     */
    public void deleteASemesterByIndex(Integer index) {
        User user = userService.findById(AuthService.userAuthenticated().getId());
        Semester semester = findSemesterByIndex(user, index);
        user.getSemester().removeIf(obj -> obj.getIndex().equals(semester.getIndex()));
        userService.save(user);
        semester.getClasses().forEach(classes -> userService.deleteAGivenClassesOfAGivenUser(user, classes.getId()));
    }

    /**
     * Create or update a {@link Semester}
     *
     * @param semester a given {@link Semester}
     * @return {@link User} with a new or updated {@link Semester}
     */
    public User createOrUpdateSemester(Semester semester) {

        User user = userService.findById(AuthService.userAuthenticated().getId());

        List<String> ids =
                userService.findAllDoneClasses() == null ? null :
                        userService.findAllDoneClasses().stream().map(Classes::getId).collect(Collectors.toList());


        HandlePossibleClassesException handler = new HandlePossibleClassesException(ids, classesService);
        semester.getClasses().forEach(classes -> {
            handler.classesCantBeDoneAtTheFirstSemester(classes.getId(), semester);
            handler.classesDontHaveThePreRequisite(classes.getId());
            handler.classAndPreRequisiteAtTheSameTime(semester, user);
        });


        if (user.getSemester() == null) {
            user.setSemester(List.of(semester));
            userService.save(user);
            return userService.findById(user.getId());
        }

        deleteAClassesWhenInsertIfAlreadyExists(semester, ids, user);

        if (user.getSemester().stream().anyMatch(obj -> obj.getIndex().equals(semester.getIndex()))) {
            findSemesterByIndex(user, semester.getIndex()).getClasses().addAll(semester.getClasses());
            userService.save(user);
            return userService.findById(user.getId());
        }

        user.getSemester().add(semester);
        userService.save(user);
        return userService.findById(user.getId());
    }

    /**
     * When you change a {@link Classes} from a {@link Semester} to another, delete it from the current {@link Semester}
     *
     * @param semester a given {@link Semester}
     * @param ids      a list of IDS of the {@link Classes} that the {@link User} already did
     * @param user     a give {@link User}
     */
    private void deleteAClassesWhenInsertIfAlreadyExists(Semester semester, List<String> ids, User user) {
        semester.getClasses().forEach(obj -> {
            if (ids != null && ids.contains(obj.getId())) {
                userService.deleteAGivenClassesOfAGivenUser(user, obj.getId());
            }
        });
    }
}
