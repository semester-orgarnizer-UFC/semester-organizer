package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException;
import com.ufc.backend.backend.exceptions.IdAlreadyExists;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClassesService classesService;


    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        if (obj.isEmpty()) throw new ObjectNotFoundException(id);
        return obj.get();
    }

    public User insert(User user) {
        return repository.save(user);
    }

    @Cacheable
    public List<Classes> findAllClasses(String id) {
        User user = findById(id);
        if (user.getSemester() == null) return null;
        List<Classes> listReturn = new ArrayList<>();
        user.getSemester().forEach(obj -> listReturn.addAll(obj.getClasses()));
        return listReturn;
    }

    public Semester getSemesterByIndex(String userId, Integer index) {
        return findById(userId).getSemester().get(index - 1);
    }

    public User insertSemester(String userId, Semester semester) {
        User user = findById(userId);
        List<Classes> classesAlreadyDone = findAllClasses(userId);
        semester.getClasses().stream()
                .map(Classes::getId).collect(Collectors.toList()).
                forEach(id -> checkIfAClassCanBeDone(id, classesAlreadyDone));

        if (user.getSemester() == null) {
            semester.setIndex(1);
            user.setSemester(List.of(semester));
            return repository.save(user);
        }

        semester.setIndex(user.getSemester().size() + 1);
        user.getSemester().add(semester);
        return repository.save(user);
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
