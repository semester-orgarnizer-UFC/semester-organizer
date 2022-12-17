package com.ufc.backend.backend.model.feedback

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Student
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

class Feedback {
    @Id
    private val id: String? = null
    private val commentary: String? = null
    private val rating: Double? = null

    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val classes: Classes? = null
    private val user: Student? = null

    @JsonIgnore
    private val actualUser: Student? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) val isAnonymous = false
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.id == (other as Feedback?)?.id
    }
}