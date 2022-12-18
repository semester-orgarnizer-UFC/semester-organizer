package com.ufc.backend.backend.model.subject
data class SubjectDto(
    val id: String,
    val name: String,
    val preRequisite: Subject?,
    val hours: Int,
    val semesterIndex: Int,
    val courses: Collection<String>
)