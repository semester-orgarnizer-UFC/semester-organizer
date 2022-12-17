package com.ufc.backend.backend.model

import com.ufc.backend.backend.commos.Identifiable
import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Classes(
    @Id
    override var id: String,
    var name: String,
    @DBRef
    var preRequisite: Classes? = null,
    var hours: Int? = null,
    var semester: Int? = null,
    @DBRef
    var teachers: Set<Teacher> = HashSet()
) : Identifiable<String> {
    companion object {
        const val serialVersionUID = 1L
    }

    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.id == (other as Classes).id
    }

    override fun toString(): String {
        return "Classes[id=$id, name=$name]"
    }
}