package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.EmailAlreadyExists
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Student
import com.ufc.backend.backend.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserService {
    @Autowired
    private val repository: UserRepository? = null

    @Autowired
    private val classesService: ClassesService? = null

    @Autowired
    private val passwordEncoder: BCryptPasswordEncoder? = null

    @Autowired
    private val courseService: CourseService? = null

    /**
     * @return all users in the system
     */
    fun findAll(): List<Student?> {
        return repository!!.findAll()
    }

    /**
     * Find a [Student] by id
     *
     * @param id the id
     * @return [Student]
     * @throws ObjectNotFoundException if the id was not found in the database
     */
    fun findById(id: String): Student {
        return repository!!.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    /**
     * Save a given [Student]
     *
     * @param user a given [Student]
     */
    fun save(user: Student?) {
        repository!!.save<Student>(user)
    }

    /**
     * Save a given [Student] into the database
     *
     * @param user a given [Student]
     * @return [Student]
     * @throws EmailAlreadyExists if the email already exists in the database
     */
    fun insert(user: Student): Student {
        if (repository!!.findByEmail(user.getEmail()) != null) throw EmailAlreadyExists(user.getEmail())
        user.setPassword(passwordEncoder!!.encode(user.getPassword()))
        user.setNotTakenClasses(courseService!!.findById(user.getCourse()).getMandatoryClasses())
        return repository.save(user)
    }

    /**
     * Find all [Classes] that has the given pre-requisite
     *
     * @param preRequisiteId the id
     * @return a list of [Classes]
     */
    fun findAllClassesThatHasTheGivenPreRequisite(preRequisiteId: String?): List<Classes>? {
        return if (findAllTakenClasses() == null) null else findAllTakenClasses()!!.stream().filter { obj: Classes ->
            obj.getPreRequisite() != null && obj.getPreRequisite().getId().equals(preRequisiteId)
        }
            .collect(Collectors.toList())
    }

    /**
     * Return all done [Classes] from the [Student]
     *
     * @return a list of [Classes]
     */
    fun findAllTakenClasses(): Set<Classes>? {
        return findById(AuthService.userAuthenticated().id).getClasses()
    }

    /**
     * Return not taken [Classes] from the [Student]
     *
     * @return a list of [Classes]
     */
    fun findAllNotTakenClasses(): Set<Classes> {
        return findById(AuthService.userAuthenticated().id).getNotTakenClasses()
    }

    fun idsOfTheTakenClasses(): List<String>? {
        return if (findAllTakenClasses() == null) null else findAllTakenClasses()!!.stream()
            .map<Any>(Classes::getId)
            .collect(Collectors.toList<Any>())
    }
}