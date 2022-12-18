package com.ufc.backend.backend.commons.model

import javax.persistence.*

@Embeddable
data class Person  (
    @Column
    var firstName: String,
    @Column
    var lastName: String,
    @Column
    var email: String,
)






