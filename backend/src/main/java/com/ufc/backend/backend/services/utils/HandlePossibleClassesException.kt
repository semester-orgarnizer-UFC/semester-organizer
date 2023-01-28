package com.ufc.backend.backend.services.utils

import com.ufc.backend.backend.exceptions.ClassCantBeDoneAtTheFirstSemester
import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException
import com.ufc.backend.backend.exceptions.ClassesAndPreRequisiteAtTheSameTimeException
import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.student.Student
import com.ufc.backend.backend.services.SubjectService
import java.util.function.Consumer

/**
 * Class responsible to throw possible exceptions when you try to create a [Semester] with invalid [Subject]
 */

// TODO: REFATORAR ISSO INTEIRO
class HandlePossibleClassesException(
    private val subjectService: SubjectService,
    private val idsAlreadyDone: Collection<String>?,
    private val semester: Semester,
    private val user: Student
) {
    fun run() {
        semester.subjects?.forEach {
            classesCantBeDoneAtTheFirstSemester(it.id, semester)
            classesDontHaveThePreRequisite(it.id)
        }
        classesAndItsPreRequisiteCantBeDoneAtTheSameTime(semester)
    }

    private fun classesDontHaveThePreRequisite(classesId: String) {
        val classes = subjectService.findById(classesId)
        if (idsAlreadyDone == null && classes.preRequisite != null) throw ClassDontHaveThePreRequisiteException(classes)
        if (idsAlreadyDone != null && classes.preRequisite != null && !idsAlreadyDone.contains(classes.preRequisite!!.id)) throw ClassDontHaveThePreRequisiteException(
            classes
        )
    }

    private fun classesCantBeDoneAtTheFirstSemester(classesId: String, semester: Semester) {
        val classes = subjectService.findById(classesId)
        if (semester.semesterIndex == 1 && classes.semesterIndex != 1) throw ClassCantBeDoneAtTheFirstSemester(classes)
    }

    private fun classesAndItsPreRequisiteCantBeDoneAtTheSameTime(semester: Semester) {
        val currentSemester = user.getSemester(semester.semesterIndex)
        semester.subjects!!.forEach(Consumer { subject: Subject ->
            if (semester.subjects!!.contains(subject.preRequisite!!)) {
                throw ClassesAndPreRequisiteAtTheSameTimeException(subject)
            }
            if (currentSemester.subjects != null && currentSemester.subjects!!.contains(subject.preRequisite)) throw ClassesAndPreRequisiteAtTheSameTimeException(
                subject
            )
        })
    }
}