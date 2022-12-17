package com.ufc.backend.backend.model.feedback

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Student
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

class Feedback(
    @Id
    override val id: String,
    private var commentary: String,
    private var rating: Double,
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val classes: Classes,
    private val user: Student,
    @JsonIgnore
    private val actualUser: Student? = null,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val isAnonymous: Boolean = false,
) : Identifiable<String>