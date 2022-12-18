package com.ufc.backend.backend.commons.model

import com.ufc.backend.backend.model.Subject
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id
import javax.persistence.*

@Embeddable
data class Person  (
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column
    override var id: String = "",
    @Column
    var firstName: String = "",
    @Column
    var lastName: String = "",
    @Column
    var email: String = "",

) : Identifiable<String>






