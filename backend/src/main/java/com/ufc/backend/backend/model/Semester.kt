package com.ufc.backend.backend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ufc.backend.backend.model.subject.Subject
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*


@Entity
@Table(name = "semesters")
class Semester
    (
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    val id: String,
    var semesterIndex: Int,
    @ManyToMany
    @JoinTable(
        name = "semester_subjects",
        joinColumns = [JoinColumn(name = "semester_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")]
    )
    var subjects: MutableSet<Subject>? = null,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    val student: Student
) {
    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.semesterIndex == (other as Semester).semesterIndex
    }

    override fun hashCode(): Int {
        return semesterIndex
    }
}