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

    public Semester findSemesterByIndex(String userId, Integer index) {
        return userService.findById(userId).getSemester().stream().filter(obj -> obj.getIndex().equals(index)).
                findFirst().orElseThrow(SemesterOutOfBoundsException::new);
    }

    public Semester findSemesterByIndex(User user, Integer index) {
        return user.getSemester().stream().filter(obj -> obj.getIndex().equals(index)).
                findFirst().orElseThrow(SemesterOutOfBoundsException::new);
    }

    public void deleteASemesterByIndex(String userId, Integer index) {
        User user = userService.findById(userId);
        Semester semester = findSemesterByIndex(user, index);
        user.getSemester().removeIf(obj -> obj.getIndex().equals(semester.getIndex()));
        userService.save(user);
        semester.getClasses().forEach(classes -> userService.deleteAGivenClassesOfAGivenUser(user, classes.getId()));
    }

    public User createOrUpdateSemester(String userId, Semester semester) {
        User user = userService.findById(userId);
        List<String> ids =
                userService.findAllDoneClasses(userId) == null ? null :
                        userService.findAllDoneClasses(userId).stream().map(Classes::getId).collect(Collectors.toList());


        HandlePossibleClassesException handler = new HandlePossibleClassesException(ids, classesService);
        semester.getClasses().forEach(classes -> {
            handler.classesCantBeDoneAtTheFirstSemester(classes.getId(), semester);
            handler.classesDontHaveThePreRequisite(classes.getId());
            handler.classAndPreRequisiteAtTheSameTime(semester, user);
        });


        if (user.getSemester() == null) {
            user.setSemester(List.of(semester));
            return userService.save(user);
        }

        deleteAClassesWhenInsertIfAlreadyExists(semester, ids, user);

        if (user.getSemester().stream().anyMatch(obj -> obj.getIndex().equals(semester.getIndex()))) {
            findSemesterByIndex(user, semester.getIndex()).getClasses().addAll(semester.getClasses());
            return userService.save(user);
        }

        user.getSemester().add(semester);
        return userService.save(user);
    }

    private void deleteAClassesWhenInsertIfAlreadyExists(Semester semester, List<String> ids, User user) {
        semester.getClasses().forEach(obj -> {
            if (ids != null && ids.contains(obj.getId())) {
                userService.deleteAGivenClassesOfAGivenUser(user, obj.getId());
            }
        });
    }
}
