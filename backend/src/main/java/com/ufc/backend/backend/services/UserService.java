package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClassesService classesService;


    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public User save(User user) {
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

    public List<Classes> findAllUndoneClasses(String id) {
        List<Classes> allClasses = classesService.findAll();
        allClasses.removeAll(findAllClasses(id));
        return allClasses;
    }

    public void deleteAGivenClassesOfAGivenUser(User user, String classId) {
        user.getSemester().forEach(semester -> semester.getClasses().removeIf(classes -> classes.getId().equals(classId)));
        save(user);
    }
}
