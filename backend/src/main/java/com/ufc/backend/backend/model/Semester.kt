package com.ufc.backend.backend.model

import lombok.*
import org.springframework.data.mongodb.core.mapping.DBRef

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Semester(
    var index: Int,
    @DBRef
    var classes: MutableSet<Classes>? = null
) {
    val actualSemesterIndex = this.index - 1
    override fun equals(other: Any?): Boolean {
        return if (javaClass != other!!.javaClass) {
            false
        } else this.index == (other as Semester).index
    }

    override fun hashCode(): Int {
        return index
    }
}