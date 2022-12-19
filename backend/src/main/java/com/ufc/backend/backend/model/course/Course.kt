package com.ufc.backend.backend.model.course

import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.model.subject.Subject
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class Course
    (
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    override val id: String,
    @Column
    var name: String,
    @Column
    var shortName: String,
    @ManyToMany
    @JoinTable(
        name = "course_mandatory_subjects",
        joinColumns = [JoinColumn(name = "course_id")],
        inverseJoinColumns = [JoinColumn(name = "subject_id")]
    )
    var mandatorySubjects: Set<Subject>,
) : Identifiable<String> {

    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.id == (other as Course).id
    }

    override fun toString(): String {
        return "Course[id=$id, name=$shortName]"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
