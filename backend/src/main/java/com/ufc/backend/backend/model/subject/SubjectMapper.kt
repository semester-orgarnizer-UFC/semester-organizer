package com.ufc.backend.backend.model.subject

import org.springframework.stereotype.Service

@Service
class SubjectMapper {
    fun entityToDto(subject: Subject) =
        SubjectDto(
            subject.id,
            subject.name,
            subject.preRequisite,
            subject.hours,
            subject.semesterIndex,
            subject.courses.map { it.name }
        )
}