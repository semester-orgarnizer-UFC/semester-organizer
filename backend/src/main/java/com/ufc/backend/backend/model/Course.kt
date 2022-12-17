package com.ufc.backend.backend.model

import com.ufc.backend.backend.commons.model.Identifiable
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

class Course(
    @Id
    override val id: String,
    var name: String,
    var shortName: String,
    @DBRef
    var mandatoryClasses: Set<Classes>,
    @DBRef
    var optionalClasses: Set<Classes>,
) : Identifiable<String> {
    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.id == (other as Course).id
    }

    override fun toString(): String {
        return "Course[id=$id, name=$shortName]"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
