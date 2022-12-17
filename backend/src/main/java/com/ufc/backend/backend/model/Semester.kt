package com.ufc.backend.backend.model

import lombok.*
import org.springframework.data.mongodb.core.mapping.DBRef

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Semester {
    private val index: Int? = null

    @DBRef
    private val classes: Set<Classes> = HashSet()
    fun equals(obj: Semester): Boolean {
        return this.getIndex() == obj.getIndex()
    }
}