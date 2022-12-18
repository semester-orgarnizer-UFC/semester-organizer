package com.ufc.backend.backend.model.feedback

import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.model.Subject
import com.ufc.backend.backend.model.Student
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
class Feedback
(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    override val id: String,
    @Column
    var commentary: String,
    @Column
    var rating: Double,
    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    var subject: Subject,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: Student,
    var isAnonymous: Boolean = false,
) : Identifiable<String>