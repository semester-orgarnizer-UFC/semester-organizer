package com.ufc.backend.backend.model.course

import com.ufc.backend.backend.model.subject.SubjectMapper
import org.springframework.stereotype.Service

@Service
class CourseMapper(
    private val mapper: SubjectMapper
) {
    fun entityToDto(course: Course) =
        CourseDto(
            course.id,
            course.name,
            course.shortName,
            course.mandatorySubjects.map { mapper.entityToDto(it) }
        )
}