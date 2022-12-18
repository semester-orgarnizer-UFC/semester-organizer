package com.ufc.backend.backend.commons.model

import com.ufc.backend.backend.model.Classes
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import javax.persistence.Embeddable

@Embeddable
data class Person  (
    @Id
    override var id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    @DBRef
    var classes: MutableSet<Classes>? = null
) : Identifiable<String>






