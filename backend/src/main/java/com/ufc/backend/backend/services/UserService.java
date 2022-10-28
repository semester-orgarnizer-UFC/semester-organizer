package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.EmailAlreadyExists;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Student;
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
    public List<Student> findAll(){
        return repository.findAll();
    }
    /**
     * Find a {@link Student} by id
     *
     * @param id the id
     * @return {@link Student}
     * @throws ObjectNotFoundException if the id was not found in the database
     */
    public Student findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    /**
     * Save a given {@link Student}
     *
     * @param user a given {@link Student}
     */
    public void save(Student user) {
        repository.save(user);
    }

    /**
     * Save a given {@link Student} into the database
     *
     * @param user a given {@link Student}
     * @return {@link Student}
     * @throws EmailAlreadyExists if the email already exists in the database
     */
    public Student insert(Student user) {
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
     * Return all done {@link Classes} from the {@link Student}
     *
     * @return a list of {@link Classes}
     */
    public Set<Classes> findAllTakenClasses() {
        return findById(AuthService.userAuthenticated().getId()).getClasses();
    }


    /**
     * Return not taken {@link Classes} from the {@link Student}
     *
     * @return a list of {@link Classes}
     */
    public Set<Classes> findAllNotTakenClasses() {
        return findById(AuthService.userAuthenticated().getId()).getNotTakenClasses();
    }
    public List<String> idsOfTheTakenClasses(){
        if(findAllTakenClasses() == null) return null;
        return findAllTakenClasses().stream().map(Classes::getId).collect(Collectors.toList());
    }
}
