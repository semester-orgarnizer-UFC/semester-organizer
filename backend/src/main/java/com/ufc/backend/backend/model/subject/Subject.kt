package com.ufc.backend.backend.model.subject

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.commons.model.FeedbackBased
import com.ufc.backend.backend.model.course.Course
import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.student.Student
import com.ufc.backend.backend.model.feedback.Feedback
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity(name = "SUBJECTS")
class Subject
    (
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    override var id: String,
    @Column
    var name: String,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pre_requisite_id", referencedColumnName = "id")
    var preRequisite: Subject? = null,
    @Column
    var hours: Int,
    @Column
    var semesterIndex: Int,
    @JsonIgnore
    @ManyToMany(mappedBy = "mandatoryClasses")
    var courses: Collection<Course>,
    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    var students: Collection<Student>,
    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    var userSemesters: Collection<Semester>,
    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    override val feedbacks: Collection<Feedback>
) : Identifiable<String>, FeedbackBased {

    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.id == (other as Subject).id
    }

    override fun toString(): String {
        return "Subject[id=$id, name=$name]"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}