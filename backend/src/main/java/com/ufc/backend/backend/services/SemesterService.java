package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.*;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            userService.save(user);
        } catch (IndexOutOfBoundsException e) {
            throw new SemesterOutOfBoundsException(index);
        }
    }

    public User createOrUpdateSemester(String userId, Semester semester) {
        User user = userService.findById(userId);
        List<Classes> classesAlreadyDone = userService.findAllClasses(userId);

        semester.getClasses().forEach(obj -> {
            List<String> ids = classesAlreadyDone == null ? null : classesAlreadyDone.stream().map(Classes::getId).collect(Collectors.toList());
            checkIfAClassCanBeDone(classesService.findById(obj.getId()), ids);
            if (ids != null && ids.contains(obj.getId())) {
                userService.deleteAGivenClassesOfAGivenUser(user, obj.getId());
            }
        });

        if (user.getSemester() == null) {
            user.setSemester(List.of(semester));
            return userService.save(user);
        }

        if(user.getSemester().stream().map(Semester::getIndex).collect(Collectors.toList()).contains(semester.getIndex())){
            user.getSemester().get(semester.getIndex() - 1).getClasses().addAll(semester.getClasses());
            return userService.save(user);
        }

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
