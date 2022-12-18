package com.ufc.backend.backend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.commons.model.FeedbackBased
import com.ufc.backend.backend.commons.model.PersonBased
import com.ufc.backend.backend.exceptions.SemesterOutOfBoundsException
import com.ufc.backend.backend.commons.model.Person
import javax.persistence.*

@Entity
class Student
    (
    @Embedded
    override val person: Person = Person(),
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String,
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    var course: Course,
    var semester: MutableList<Semester>? = null,
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "students_subjects",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")]
    )
    var subjects: MutableSet<Subject>? = null
) : PersonBased, FeedbackBased() {

    private val newSemesterIndex = semester?.size?.minus(1) ?: 1

    fun getSemester(index: Int): Semester = semester?.get(index) ?: throw SemesterOutOfBoundsException()

    fun addEmptySemester() {
        semester?.plus(Semester(newSemesterIndex, null))
    }

    fun updateSemester(newSemester: Semester) {
        val oldSemester = getSemester(newSemester.actualSemesterIndex)
        newSemester.subject?.let { oldSemester.subject?.addAll(it) }
        notifyTakenClasses(newSemester.subject)
    }

    private fun notifyTakenClasses(subject: Collection<Subject>?) {
        if (subject != null) {
            subjects?.addAll(subject)
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