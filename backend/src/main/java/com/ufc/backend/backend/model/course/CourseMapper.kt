package com.ufc.backend.backend.model.course

import com.ufc.backend.backend.model.subject.SubjectMapper
object CourseMapper {
    fun entityToDto(course: Course) =
        CourseDto(
            course.id,
            course.name,
            course.shortName,
            course.mandatorySubjects.map { SubjectMapper.entityToDto(it) }
        )
}