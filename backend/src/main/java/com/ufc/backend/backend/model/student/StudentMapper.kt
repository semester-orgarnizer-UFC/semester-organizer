package com.ufc.backend.backend.model.student

import com.ufc.backend.backend.commons.model.Person
import com.ufc.backend.backend.model.course.Course
import com.ufc.backend.backend.model.student.dto.StudentDto
import com.ufc.backend.backend.model.student.dto.StudentInsertDto
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentMapper {

    fun entityToDto(student: Student) =
        StudentDto(
            student.id,
            student.person.firstName,
            student.person.lastName,
            student.person.email,
            student.course.shortName
        )

    fun insertDtoToEntity(dto: StudentInsertDto, course: Course) =
        Student(
            UUID.randomUUID().toString(),
            Person(dto.firstName, dto.lastName, dto.email),
            dto.password,
            course,
            null
        )

}