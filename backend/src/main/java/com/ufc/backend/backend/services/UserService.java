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
}
