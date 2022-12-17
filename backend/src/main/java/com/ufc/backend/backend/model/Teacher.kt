package com.ufc.backend.backend.model

import com.ufc.backend.backend.commons.model.PersonBased
import com.ufc.backend.backend.commons.model.Person
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Teacher : PersonBased {
    override val person: Person = Person()
}