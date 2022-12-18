package com.ufc.backend.backend.services.utils

import com.ufc.backend.backend.exceptions.ClassCantBeDoneAtTheFirstSemester
import com.ufc.backend.backend.exceptions.ClassDontHaveThePreRequisiteException
import com.ufc.backend.backend.exceptions.ClassesAndPreRequisiteAtTheSameTimeException
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.Student
import com.ufc.backend.backend.services.ClassesService
import java.util.function.Consumer

/**
 * Class responsible to throw possible exceptions when you try to create a [Semester] with invalid [Classes]
 */
class HandlePossibleClassesException(
    private val classesService: ClassesService,
    private val idsAlreadyDone: Collection<String>?,
    private val semester: Semester,
    private val user: Student
) {
    fun run() {
        semester.classes?.forEach {
            classesCantBeDoneAtTheFirstSemester(it.id, semester)
            classesDontHaveThePreRequisite(it.id)
        }
        classesAndItsPreRequisiteCantBeDoneAtTheSameTime(semester)
    }

    private fun classesDontHaveThePreRequisite(classesId: String) {
        val classes = classesService.findById(classesId)
        if (idsAlreadyDone == null && classes.preRequisite != null) throw ClassDontHaveThePreRequisiteException(classes)
        if (idsAlreadyDone != null && classes.preRequisite != null && !idsAlreadyDone.contains(classes.preRequisite!!.id)) throw ClassDontHaveThePreRequisiteException(
            classes
        )
    }

    private fun classesCantBeDoneAtTheFirstSemester(classesId: String, semester: Semester) {
        val classes = classesService.findById(classesId)
        if (semester.index == 1 && classes.semester != 1) throw ClassCantBeDoneAtTheFirstSemester(classes)
    }

    private fun classesAndItsPreRequisiteCantBeDoneAtTheSameTime(semester: Semester) {
        val currentSemester = user.getSemester(semester.index)
        semester.classes!!.forEach(Consumer { classes: Classes ->
            if (semester.classes!!.contains(classes.preRequisite!!)) {
                throw ClassesAndPreRequisiteAtTheSameTimeException(classes)
            }
            if (currentSemester.classes != null && currentSemester.classes!!.contains(classes.preRequisite)) throw ClassesAndPreRequisiteAtTheSameTimeException(
                classes
            )
        })
    }
}