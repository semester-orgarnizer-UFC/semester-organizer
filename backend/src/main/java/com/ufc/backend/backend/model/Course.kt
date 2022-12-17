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
class Course : FeedbackBased() {
    @Id
    private val id: String? = null
    private val name: String? = null
    private val shortName: String? = null

    @DBRef
    private val mandatoryClasses: Set<Classes>? = null

    @DBRef
    private val optionalClasses: Set<Classes>? = null
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return if (javaClass != obj!!.javaClass) {
            false
        } else this.getId() == (obj as Course?).getId()
    }
}