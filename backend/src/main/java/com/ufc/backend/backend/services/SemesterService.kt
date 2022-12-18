package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.SemesterOutOfBoundsException
import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.Student
import com.ufc.backend.backend.services.utils.HandlePossibleClassesException
import org.springframework.stereotype.Service

@Service
class SemesterService(
    private val userService: UserService,
    private val classesService: ClassesService
) {

    /**
     * Return a [Semester] from the logged [Student]
     *
     * @param index the index of the semesters
     * @return [Semester]
     */
    fun findSemesterByIndex(index: Int, id: String): Semester {
        val user = userService.findById(id)
        return user.getSemester(index)
    }

    /**
     * Return a list containing all [Semester]
     *
     * @return a list of [Semester]
     */
    fun findAll(id: String): Collection<Semester>? {
        return userService.findById(id).semester
    }

    /**
     * Return a [Semester] from a given [Student]
     *
     * @param user  the given user
     * @param index the index of the semester
     * @return [Semester]
     */
    fun findSemesterByIndex(user: Student, index: Int): Semester {
        return user.semester?.firstOrNull { it.semesterIndex == index } ?: throw SemesterOutOfBoundsException()
    }

    /**
     * Delete a [Semester] by its index
     *
     * @param index the index of the semesters that should be deleted
     */
    fun deleteASemesterByIndex(index: Int, id: String) {
        val user = userService.findById(id)
        val semester = findSemesterByIndex(user, index)
        user.semester?.remove(semester)
        userService.save(user)
        // semester.getSubject().forEach(subject -> userService.deleteClassFromSemester(subject.getId(), user));
    }

    /**
     * Create a empty [Semester]
     *
     * @return [Student]
     */
    fun createEmptySemester(id: String): Student {
        val user = userService.findById(id)
        user.addEmptySemester()
        userService.save(user)
        return userService.findById(id)
    }

    /**
     * Update a [Semester]
     *
     * @param semester a given [Semester]
     * @return [Student] with an updated [Semester]
     */
    fun updateSemester(semester: Semester, id: String): Student? {
        val user = userService.findById(id)
        HandlePossibleClassesException(classesService, userService.idsOfTheTakenClasses(id), semester, user).run()
        semester.subjects?.let { deleteAClassesWhenInsertIfAlreadyExists(it, user) }
        user.updateSemester(semester)
        userService.save(user)
        return userService.findById(id)
    }

    /**
     * When you change a [Subject] from a [Semester] to another, delete it from the current [Semester]
     *
     * @param subjectThatShouldBeDeleted a given list of [Subject]
     */
    private fun deleteAClassesWhenInsertIfAlreadyExists(subjectThatShouldBeDeleted: Set<Subject>, user: Student) {
        user.semester?.forEach{it.subjects?.removeAll(subjectThatShouldBeDeleted)}
    }
}