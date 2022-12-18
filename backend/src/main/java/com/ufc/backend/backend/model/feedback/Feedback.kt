package com.ufc.backend.backend.model.feedback

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Student
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.beans.ConstructorProperties

@Document
class Feedback
@ConstructorProperties(
    "id",
    "commentary",
    "rating",
    "classes",
    "user",
    "actualUser",
    "isAnonymous",
)
constructor(
    @Id
    override val id: String,
    var commentary: String,
    var rating: Double,
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var classes: Classes,
    var user: Student,
    @JsonIgnore
    var actualUser: Student? = null,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var isAnonymous: Boolean = false,
) : Identifiable<String>