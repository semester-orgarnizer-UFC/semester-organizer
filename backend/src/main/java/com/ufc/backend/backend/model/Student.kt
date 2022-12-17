package com.ufc.backend.backend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.commons.model.PersonBased
import com.ufc.backend.backend.exceptions.SemesterOutOfBoundsException
import com.ufc.backend.backend.commons.model.Person

class Student(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    var course: String,
    var semester: List<Semester>? = null,
    @JsonIgnore
    var notTakenClasses: MutableSet<Classes>? = null,
) : PersonBased {
    override val person: Person = Person()

    private val newSemesterIndex = semester?.size?.minus(1) ?: 1

    fun getSemester(index: Int): Semester = semester?.get(index) ?: throw SemesterOutOfBoundsException()

    fun addEmptySemester() {
        semester?.plus(Semester(newSemesterIndex, null))
    }

    fun updateSemester(newSemester: Semester) {
        val oldSemester = getSemester(newSemester.actualSemesterIndex)
        newSemester.classes?.let { oldSemester.classes?.addAll(it) }
        notifyTakenClasses(newSemester.classes)
        notifyNotTakenClasses(newSemester.classes)
    }

    private fun notifyTakenClasses(classes: Collection<Classes>?) {
        if (classes != null) {
            person.classes?.addAll(classes)
        }
    }

    private fun notifyNotTakenClasses(classes: Collection<Classes>?) {
        if (classes != null) {
            notTakenClasses?.removeAll(classes.toSet())
        }
    }

    override fun equals(obj: Any?): Boolean {
        return if (javaClass != obj!!.javaClass)
            false
        else person.id == (obj as Student?)!!.person.id
    }

    override fun hashCode(): Int {
        return person.id.hashCode()
    }
}