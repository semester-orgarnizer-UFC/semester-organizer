package com.ufc.backend.backend.model


data class Semester
    (
    var index: Int,
    var subject: MutableSet<Subject>? = null
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