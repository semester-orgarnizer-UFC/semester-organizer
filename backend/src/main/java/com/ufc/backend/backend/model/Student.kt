package com.ufc.backend.backend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.commons.model.FeedbackBased
import com.ufc.backend.backend.commons.model.PersonBased
import com.ufc.backend.backend.exceptions.SemesterOutOfBoundsException
import com.ufc.backend.backend.commons.model.Person
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.beans.ConstructorProperties
import javax.persistence.Embedded

@Document
class Student
@ConstructorProperties
    (
    "person",
    "password",
    "course",
    "semester",
    "notTakenClasses",
)
constructor(
    @Embedded
    override val person: Person = Person(),
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    @JsonIgnore
    @DBRef
    var course: Course,
    var semester: MutableList<Semester>? = null,
    @JsonIgnore
    var notTakenClasses: MutableSet<Classes>? = null,
) : PersonBased, FeedbackBased() {

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

    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass)
            false
        else person.id == (other as Student?)!!.person.id
    }

    override fun hashCode(): Int {
        return person.id.hashCode()
    }
}