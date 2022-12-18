package com.ufc.backend.backend.model

import org.springframework.data.mongodb.core.mapping.DBRef
import java.beans.ConstructorProperties

data class Semester
@ConstructorProperties
    (
    "index",
    "classes"
)
constructor(
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