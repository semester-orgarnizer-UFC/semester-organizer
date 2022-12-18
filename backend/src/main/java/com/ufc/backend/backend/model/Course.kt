package com.ufc.backend.backend.model

import com.ufc.backend.backend.commons.model.Identifiable
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id
import javax.persistence.*

@Entity
data class Course
(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
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
    var mandatoryClasses: Set<Subject>,
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
