package com.ufc.backend.backend.model

import com.ufc.backend.backend.model.based.FeedbackBased
import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
class Classes : FeedbackBased() {
    @Id
    private val id: String? = null
    private val name: String? = null

    @DBRef
    private val preRequisite: Classes? = null
    private val hours: Int? = null
    private val semester: Int? = null

    @DBRef
    private val teachers: Set<Teacher> = HashSet()
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return if (javaClass != obj!!.javaClass) {
            false
        } else this.id == (obj as Classes).id
    }

    override fun toString(): String {
        return "Classes[id=$id, name=$name]"
    }
}