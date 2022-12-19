package com.ufc.backend.backend.model.course

import com.ufc.backend.backend.model.subject.SubjectDto

data class CourseDto(
    val id: String,
    val name: String,
    val shortName: String,
    val subjects: Collection<SubjectDto>
)
