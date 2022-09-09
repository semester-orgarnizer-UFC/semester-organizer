package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.EmailAlreadyExists;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
        return repository.save(user);
    }


    /**
     * Return all done {@link Classes} from the {@link User}
     *
     * @return a list of {@link Classes}
     */
    @Cacheable
    public List<Classes> findAllDoneClasses() {
        User user = findById(AuthService.userAuthenticated().getId());
        if (user.getSemester() == null) return null;
        List<Classes> listReturn = new ArrayList<>();
        user.getSemester().forEach(obj -> {
            if (obj.getClasses() != null)
                listReturn.addAll(obj.getClasses());
        });
        return listReturn;
    }

    /**
     * Find all {@link Classes} that has the given pre requisite
     *
     * @param preRequisiteId the id
     * @return a list of {@link Classes}
     */
    public List<Classes> findAllClassesThatHasTheGivenPreRequisite(String preRequisiteId) {
        return findAllDoneClasses().stream().filter(obj -> obj.getPreRequisite() != null && obj.getPreRequisite().getId().equals(preRequisiteId)).collect(Collectors.toList());
    }

    /**
     * Return not taken {@link Classes} from the {@link User}
     *
     * @return a list of {@link Classes}
     */
    @Cacheable
    public List<Classes> findAllNotTakenClasses() {
        List<Classes> classesDone = findAllDoneClasses();
        List<Classes> allClasses = classesService.findAll();

        if (classesDone == null) return allClasses;

        classesDone.forEach(classes -> allClasses.removeIf(obj -> obj.getId().equals(classes.getId())));
        return allClasses;
    }

    /**
     * Delete a {@link Classes} from a {@link Semester}
     *
     * @param user    a given {@link User}
     * @param classId the id that should be deleted
     */
    public void deleteAGivenClassesOfAGivenUser(User user, String classId) {
        user.getSemester().forEach(semester -> semester.getClasses().removeIf(classes -> classes.getId().equals(classId)));
        save(user);

        if (findAllClassesThatHasTheGivenPreRequisite(classId) != null) {
            findAllClassesThatHasTheGivenPreRequisite(classId).forEach(classes -> deleteAGivenClassesOfAGivenUser(user, classes.getId()));
        }
    }
}
