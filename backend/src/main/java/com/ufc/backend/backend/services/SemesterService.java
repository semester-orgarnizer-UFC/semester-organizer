package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException;
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


    public Semester getSemesterByIndex(String userId, Integer index) {
        try {
            return userService.findById(userId).getSemester().get(index - 1);
        }catch(IndexOutOfBoundsException e){
            throw new SemesterOutOfBoundsException(index);
        }
    }

    public User insertSemester(String userId, Semester semester) {
        User user = userService.findById(userId);
        List<Classes> classesAlreadyDone = userService.findAllClasses(userId);
        semester.getClasses().stream()
                .map(Classes::getId).collect(Collectors.toList()).
                forEach(id -> checkIfAClassCanBeDone(id, classesAlreadyDone));

        if (user.getSemester() == null) {
            semester.setIndex(1);
            user.setSemester(List.of(semester));
            return userRepository.save(user);
        }

        semester.setIndex(user.getSemester().size() + 1);
        user.getSemester().add(semester);
        return userRepository.save(user);
    }

    private void checkIfAClassCanBeDone(String classId, List<Classes> classesAlreadyDone) {
        Classes classes = classesService.findById(classId);
        List<String> ids = new ArrayList<>();

        if (classesAlreadyDone != null) {
            ids = classesAlreadyDone.stream().map(Classes::getId).collect(Collectors.toList());
        }

        if (!ids.isEmpty() && ids.contains(classId)) {
            throw new IdAlreadyExists(classId);
        }

        if (ids.isEmpty() && classes.getPreRequisite() != null) {
            throw new ClassDontHaveThePreRequisiteException(classes, classes.getPreRequisite());
        }
        if (
                classesAlreadyDone != null
                        && classes.getPreRequisite() != null
                        && !ids.contains(classes.getPreRequisite().getId())
        )
            throw new ClassDontHaveThePreRequisiteException(classes, classes.getPreRequisite());
    }
}
