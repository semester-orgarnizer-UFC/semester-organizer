package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.*;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.utils.HandlePossibleClassesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


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
        return user.getSemester().get(index - 1);
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
        user.getSemester().removeIf(obj -> obj.equals(semester));
        userService.save(user);
        // semester.getClasses().forEach(classes -> userService.deleteClassFromSemester(classes.getId(), user));
    }

    /**
     * Create a empty {@link Semester}
     *
     * @return {@link User}
     */
    public User createEmptySemester() {
        User user = userService.findById(AuthService.userAuthenticated().getId());
        user.addEmptySemester();
        userService.save(user);
        return userService.findById(user.getId());
    }

    /**
     * Update a {@link Semester}
     *
     * @param semester a given {@link Semester}
     * @return {@link User} with an updated {@link Semester}
     */
    public User updateSemester(Semester semester) {
        User user = userService.findById(AuthService.userAuthenticated().getId());
        new HandlePossibleClassesException(classesService, userService.idsOfTheTakenClasses(), semester, user).run();

        deleteAClassesWhenInsertIfAlreadyExists(semester.getClasses(), user);
        user.updateSemester(semester);
        userService.save(user);
        return userService.findById(user.getId());
    }


    /**
     * When you change a {@link Classes} from a {@link Semester} to another, delete it from the current {@link Semester}
     *
     * @param classesThatShouldBeDeleted a given list of {@link Classes}
     */
    private void deleteAClassesWhenInsertIfAlreadyExists(Set<Classes> classesThatShouldBeDeleted, User user) {
        user.getSemester().forEach(userSemesters -> {
            if (userSemesters.getClasses() != null) {
                classesThatShouldBeDeleted.forEach(classesObj -> userSemesters.getClasses().removeIf(classes -> classes.equals(classesObj)));
            }
        });
    }
}
