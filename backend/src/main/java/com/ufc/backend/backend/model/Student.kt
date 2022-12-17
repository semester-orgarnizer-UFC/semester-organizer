package com.ufc.backend.backend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.backend.backend.model.based.PersonBased
import lombok.*
import org.springframework.data.mongodb.core.mapping.Document

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
class Student : PersonBased() {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val password: String? = null
    private val course: String? = null
    private val semester: List<Semester> = ArrayList()

    @JsonIgnore
    private val notTakenClasses: Set<Classes> = HashSet()
    fun addEmptySemester() {
        val index = if (semester.isEmpty()) 1 else this.getSemester().size + 1
        this.getSemester().add(Semester(index, null))
    }

    fun updateSemester(newSemester: Semester) {
        val oldSemester: Semester = this.getSemester().get(newSemester.getIndex() - 1)
        oldSemester.getClasses().addAll(newSemester.getClasses())
        notifyTakenClasses(newSemester.getClasses())
        notifyNotTakenClasses(newSemester.getClasses())
    }

    private fun notifyTakenClasses(classes: Set<Classes>) {
        getClasses().addAll(classes)
    }

    private fun notifyNotTakenClasses(classes: Set<Classes>) {
        getNotTakenClasses().removeAll(classes)
    }

    override fun equals(obj: Any?): Boolean {
        return if (javaClass != obj!!.javaClass) false else id == (obj as Student?)!!.id
    }
}