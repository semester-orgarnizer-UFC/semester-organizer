package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Cacheable
    public List<Classes> findAllDoneClasses() {
        User user = findById(AuthService.userAuthenticated().getId());

        if (user.getSemester() == null) return null;
        List<Classes> listReturn = new ArrayList<>();
        user.getSemester().forEach(obj -> listReturn.addAll(obj.getClasses()));
        return listReturn;
    }


    public List<Classes> findAllClassesThatHasTheGivenPreRequisite(String preRequisiteId) {
        return findAllDoneClasses().stream().filter(obj -> obj.getPreRequisite() != null && obj.getPreRequisite().getId().equals(preRequisiteId)).collect(Collectors.toList());
    }


    @Cacheable
    public List<Classes> findAllUndoneClasses() {
       List<Classes> classesDone = findAllDoneClasses();
       List<Classes> allClasses = classesService.findAll();

       if(classesDone == null) return allClasses;

       classesDone.forEach(classes -> allClasses.removeIf(obj -> obj.getId().equals(classes.getId())));
       return allClasses;
    }


    public void deleteAGivenClassesOfAGivenUser(User user, String classId) {
        user.getSemester().forEach(semester -> semester.getClasses().removeIf(classes -> classes.getId().equals(classId)));
        save(user);

        if (findAllClassesThatHasTheGivenPreRequisite(classId) != null) {
            findAllClassesThatHasTheGivenPreRequisite(classId).forEach(classes -> deleteAGivenClassesOfAGivenUser(user, classes.getId()));
        }
    }

}
