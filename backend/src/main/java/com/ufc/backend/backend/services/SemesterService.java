package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException;
import com.ufc.backend.backend.exceptions.ClassesAndPreRequisiteAtTheSameTimeException;
import com.ufc.backend.backend.exceptions.IdAlreadyExists;
import com.ufc.backend.backend.exceptions.SemesterOutOfBoundsException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
            userRepository.save(user);
        } catch (IndexOutOfBoundsException e) {
            throw new SemesterOutOfBoundsException(index);
        }
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
            });

            user.getSemester().get(index - 1).getClasses().addAll(updatedSemester.getClasses());
            user.getSemester().get(index - 1).setIndex(index);
            userRepository.save(user);
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
            return userRepository.save(user);
        }

        semester.setIndex(user.getSemester().size() + 1);
        user.getSemester().add(semester);
        return userRepository.save(user);
    }

    private void checkIfAClassCanBeDone(Classes classes, List<String> idsAlreadyDone) {

        if (idsAlreadyDone == null) {
            if (classes.getPreRequisite() != null)
                throw new ClassDontHaveThePreRequisiteException(classes);
        } else {
            if (idsAlreadyDone.contains(classes.getId()))
                throw new IdAlreadyExists(classes.getId());

            if (classes.getPreRequisite() != null && !idsAlreadyDone.contains(classes.getPreRequisite().getId()))
                throw new ClassDontHaveThePreRequisiteException(classes);
        }
    }
}
