package com.ufc.backend.backend.services

import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.Student
import com.ufc.backend.backend.services.utils.HandlePossibleClassesException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SemesterService {
    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val classesService: ClassesService? = null

    /**
     * Return a [Semester] from the logged [Student]
     *
     * @param index the index of the semesters
     * @return [Semester]
     */
    fun findSemesterByIndex(index: Int): Semester {
        val user = userService!!.findById(AuthService.userAuthenticated().id)
        return user.getSemester().get(index - 1)
    }

    /**
     * Return a list containing all [Semester]
     *
     * @return a list of [Semester]
     */
    fun findAll(): List<Semester> {
        return userService!!.findById(AuthService.userAuthenticated().id).getSemester()
    }

    /**
     * Return a [Semester] from a given [Student]
     *
     * @param user  the given user
     * @param index the index of the semester
     * @return [Semester]
     */
    fun findSemesterByIndex(user: Student?, index: Int?): Semester {
        return user.getSemester().stream().filter { obj -> obj.getIndex().equals(index) }.findFirst()
            .orElseThrow { SemesterOutOfBoundsException() }
    }

    /**
     * Delete a [Semester] by its index
     *
     * @param index the index of the semesters that should be deleted
     */
    fun deleteASemesterByIndex(index: Int?) {
        val user = userService!!.findById(AuthService.userAuthenticated().id)
        val semester = findSemesterByIndex(user, index)
        user.getSemester().remove(semester)
        userService.save(user)
        // semester.getClasses().forEach(classes -> userService.deleteClassFromSemester(classes.getId(), user));
    }

    /**
     * Create a empty [Semester]
     *
     * @return [Student]
     */
    fun createEmptySemester(): Student? {
        val user = userService!!.findById(AuthService.userAuthenticated().id)
        user!!.addEmptySemester()
        userService.save(user)
        return userService.findById(user.getId())
    }

    /**
     * Update a [Semester]
     *
     * @param semester a given [Semester]
     * @return [Student] with an updated [Semester]
     */
    fun updateSemester(semester: Semester): Student? {
        val user = userService!!.findById(AuthService.userAuthenticated().id)
        HandlePossibleClassesException(classesService, userService.idsOfTheTakenClasses(), semester, user).run()
        deleteAClassesWhenInsertIfAlreadyExists(semester.getClasses(), user)
        user!!.updateSemester(semester)
        userService.save(user)
        return userService.findById(user.getId())
    }

    /**
     * When you change a [Classes] from a [Semester] to another, delete it from the current [Semester]
     *
     * @param classesThatShouldBeDeleted a given list of [Classes]
     */
    private fun deleteAClassesWhenInsertIfAlreadyExists(classesThatShouldBeDeleted: Set<Classes>, user: Student?) {
        user.getSemester().forEach { userSemesters ->
            if (userSemesters.getClasses() != null) {
                userSemesters.getClasses().removeAll(classesThatShouldBeDeleted)
            }
        }
    }
}