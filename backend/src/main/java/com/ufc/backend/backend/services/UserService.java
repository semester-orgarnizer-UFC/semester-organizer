package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.EmailAlreadyExists;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Course;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CourseService courseService;


    /**
     * @return all users in the system
     */
    public List<User> findAll(){
        return repository.findAll();
    }
    /**
     * Find a {@link User} by id
     *
     * @param id the id
     * @return {@link User}
     * @throws ObjectNotFoundException if the id was not found in the database
     */
    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    /**
     * Save a given {@link User}
     *
     * @param user a given {@link User}
     */
    public void save(User user) {
        repository.save(user);
    }

    /**
     * Save a given {@link User} into the database
     *
     * @param user a given {@link User}
     * @return {@link User}
     * @throws EmailAlreadyExists if the email already exists in the database
     */
    public User insert(User user) {
        if (repository.findByEmail(user.getEmail()) != null) throw new EmailAlreadyExists(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setNotTakenClasses(courseService.findById(user.getCourse()).getMandatoryClasses());
        return repository.save(user);
    }

    /**
     * Find all {@link Classes} that has the given pre-requisite
     *
     * @param preRequisiteId the id
     * @return a list of {@link Classes}
     */
    public List<Classes> findAllClassesThatHasTheGivenPreRequisite(String preRequisiteId) {
        if (findAllTakenClasses() == null) return null;
        return findAllTakenClasses().stream().filter(obj -> obj.getPreRequisite() != null && obj.getPreRequisite().getId().equals(preRequisiteId)).collect(Collectors.toList());
    }

    /**
     * Return all done {@link Classes} from the {@link User}
     *
     * @return a list of {@link Classes}
     */
    @Cacheable
    public List<Classes> findAllTakenClasses() {
        return findById(AuthService.userAuthenticated().getId()).getTakenClasses();
    }


    /**
     * Return not taken {@link Classes} from the {@link User}
     *
     * @return a list of {@link Classes}
     */
    @Cacheable
    public List<Classes> findAllNotTakenClasses() {
        return findById(AuthService.userAuthenticated().getId()).getNotTakenClasses();
    }
    public List<String> idsOfTheTakenClasses(){
        if(findAllTakenClasses() == null) return null;
        return findAllTakenClasses().stream().map(Classes::getId).collect(Collectors.toList());
    }
}
