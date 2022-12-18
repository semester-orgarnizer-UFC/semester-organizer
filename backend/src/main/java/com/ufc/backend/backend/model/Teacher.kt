package com.ufc.backend.backend.model

import com.ufc.backend.backend.commons.model.PersonBased
import com.ufc.backend.backend.commons.model.Person
import java.beans.ConstructorProperties
import javax.persistence.Embedded
import javax.persistence.FetchType
import javax.persistence.OneToMany

data class Teacher
@ConstructorProperties("person")
constructor(
    @Embedded
    override val person: Person = Person(),
    @OneToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    var subjects: MutableSet<Subject>? = null
) : PersonBased
