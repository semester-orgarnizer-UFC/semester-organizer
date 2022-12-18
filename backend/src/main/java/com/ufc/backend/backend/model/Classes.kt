package com.ufc.backend.backend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ufc.backend.backend.commons.model.Identifiable
import com.ufc.backend.backend.commons.model.FeedbackBased
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.beans.ConstructorProperties

@Document
class Classes
@ConstructorProperties
    (
    "id",
    "name",
    "preRequisite",
    "hours",
    "semester",
    "teachers",
    "preRequisiteId",
)
constructor(
    @Id
    override var id: String,
    var name: String,
    @DBRef
    var preRequisite: Classes? = null,
    var hours: Int,
    var semester: Int,
    @DBRef
    var teachers: Set<Teacher>? = null,
) : Identifiable<String>, FeedbackBased() {

    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.id == (other as Classes).id
    }

    override fun toString(): String {
        return "Classes[id=$id, name=$name]"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}