package com.ufc.backend.backend.model

import com.ufc.backend.backend.commons.model.PersonBased
import com.ufc.backend.backend.commons.model.Person
import org.springframework.data.mongodb.core.mapping.Document
import java.beans.ConstructorProperties
import javax.persistence.Embedded

data class Teacher
@ConstructorProperties("person")
constructor(
    @Embedded
    override val person: Person = Person()
) : PersonBased
