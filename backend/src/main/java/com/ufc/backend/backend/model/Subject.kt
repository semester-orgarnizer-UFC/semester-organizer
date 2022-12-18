package com.ufc.backend.backend.model

import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.commons.model.FeedbackBased
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id
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
    var semester: Int,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    var teachers: Set<Teacher>? = null,
    @ManyToMany(mappedBy = "mandatoryClasses")
    var courses: Collection<Course>,
    @ManyToMany(mappedBy = "subjects")
    var students: Collection<Student>
) : Identifiable<String>, FeedbackBased() {

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