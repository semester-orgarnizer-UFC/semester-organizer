package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.*;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SemesterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassesService classesService;

    public Semester findSemesterByIndex(String userId, Integer index) {
        try {
            return userService.findById(userId).getSemester().get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SemesterOutOfBoundsException(index);
        }
    }

    public void deleteASemesterByIndex(String userId, Integer index) {
        try {
            User user = userService.findById(userId);
            user.getSemester().remove(index - 1);
            userService.save(user);
        } catch (IndexOutOfBoundsException e) {
            throw new SemesterOutOfBoundsException(index);
        }
    }
    public void deleteAGivenClassesOfAGivenSemester(String userId, Integer index, String classId){
        User user = userService.findById(userId);
        user.getSemester().get(index - 1).getClasses().removeIf( obj -> Objects.equals(obj.getId(), classId));
        userService.save(user);
    }
    public Semester updateSemester(String userId, Integer index, Semester updatedSemester) {
        try {
            User user = userService.findById(userId);
            List<Classes> classesAlreadyDone = userService.findAllClasses(userId);

            updatedSemester.getClasses().forEach(obj -> {
                Classes classes = classesService.findById(obj.getId());
                List<String> ids = classesAlreadyDone == null ? null : classesAlreadyDone.stream().map(Classes::getId).collect(Collectors.toList());
                checkIfAClassCanBeDone(classes, ids);

                if (classes.getPreRequisite() != null && ids != null && ids.contains(classes.getPreRequisite().getId()))
                    throw new ClassesAndPreRequisiteAtTheSameTimeException(classes);

                if (ids != null && ids.contains(classes.getId())){
                    deleteAGivenClassesOfAGivenSemester(userId, index, classes.getId());
                }
            });

            user.getSemester().get(index - 1).getClasses().addAll(updatedSemester.getClasses());
            user.getSemester().get(index - 1).setIndex(index);
            userService.save(user);
            return user.getSemester().get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SemesterOutOfBoundsException(index);
        }
    }

    public User insertSemester(String userId, Semester semester) {
        User user = userService.findById(userId);
        List<Classes> classesAlreadyDone = userService.findAllClasses(userId);

        semester.getClasses().forEach(obj -> {
            List<String> ids = classesAlreadyDone == null ? null : classesAlreadyDone.stream().map(Classes::getId).collect(Collectors.toList());
            checkIfAClassCanBeDone(classesService.findById(obj.getId()), ids);
        });

        if (user.getSemester() == null) {
            semester.setIndex(1);
            user.setSemester(List.of(semester));

            semester.getClasses().forEach(obj -> {
                Classes classes = classesService.findById(obj.getId());
                if (classes.getSemester() != 1)
                    throw new ClassCantBeDoneAtTheFirstSemester(classes);
            });
            return userService.save(user);
        }

        semester.setIndex(user.getSemester().size() + 1);
        user.getSemester().add(semester);
        return userService.save(user);
    }

    private void checkIfAClassCanBeDone(Classes classes, List<String> idsAlreadyDone) {

        if (idsAlreadyDone == null) {
            if (classes.getPreRequisite() != null)
                throw new ClassDontHaveThePreRequisiteException(classes);
        } else {
            if (classes.getPreRequisite() != null && !idsAlreadyDone.contains(classes.getPreRequisite().getId()))
                throw new ClassDontHaveThePreRequisiteException(classes);
        }
    }
}
