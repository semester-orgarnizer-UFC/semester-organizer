package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.EmailAlreadyExists
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.student.Student
import com.ufc.backend.backend.model.student.dto.StudentInsertDto
import com.ufc.backend.backend.model.student.StudentMapper
import com.ufc.backend.backend.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository,
    private val courseService: CourseService,
    private val mapper: StudentMapper,
    private val passwordEncoder: BCryptPasswordEncoder
) {


    /**
     * @return all users in the system
     */
    fun findAll(): Collection<Student> {
        return repository.findAll()
    }

    /**
     * Find a [Student] by id
     *
     * @param id the id
     * @return [Student]
     * @throws ObjectNotFoundException if the id was not found in the database
     */
    fun findById(id: String): Student {
        return repository.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    /**
     * Save a given [Student]
     *
     * @param user a given [Student]
     */
    fun save(user: Student) {
        repository.save(user)
    }

    /**
     * Save a given [Student] into the database
     *
     * @param user a given [Student]
     * @return [Student]
     * @throws EmailAlreadyExists if the email already exists in the database
     */
    fun insert(user: StudentInsertDto): Student {
        user.password = passwordEncoder.encode(user.password)
        if (repository.findByPersonEmail(user.email) != null)
            throw EmailAlreadyExists(user.email)
        val course = courseService.findById(user.course)
        val entity = mapper.insertDtoToEntity(user, course)
        return repository.save(entity)
    }

    /**
     * Find all [Subject] that has the given pre-requisite
     *
     * @param preRequisiteId the id
     * @return a list of [Subject]
     */
    fun findAllClassesThatHasTheGivenPreRequisite(preRequisiteId: String, id: String): Collection<Subject> {
        return findAllTakenClasses(id).filter { it.preRequisite?.id == preRequisiteId }
            ?: throw ObjectNotFoundException(id)
    }

    /**
     * Return all done [Subject] from the [Student]
     *
     * @return a list of [Subject]
     */
    fun findAllTakenClasses(id: String): Set<Subject> {
        return findById(id).subjects ?: setOf()
    }

    fun idsOfTheTakenClasses(id: String): Collection<String> {
        return findAllTakenClasses(id).map { it.id }
    }
}