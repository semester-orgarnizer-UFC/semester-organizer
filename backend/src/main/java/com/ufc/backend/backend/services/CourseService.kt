package com.ufc.backend.backend.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.course.Course
import com.ufc.backend.backend.repositories.SubjectRepository
import com.ufc.backend.backend.repositories.CourseRepository
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val subjectRepository: SubjectRepository
) {

    fun populateCourses() {
        try {
            val file = File(Objects.requireNonNull(this.javaClass.classLoader.getResource("courses/cc.json")).file)
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            val course = mapper.readValue(file, Course::class.java)
            subjectRepository.saveAll(course.mandatorySubjects)
            courseRepository.save(mapper.readValue(file, Course::class.java))
        } catch (e: Exception) {
            throw e
        }
    }

    fun findById(id: String): Course {
        return courseRepository.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun findAll(): Collection<Course> {
        return courseRepository.findAll()
    }

    fun findMandatory(id: String): Collection<Subject> {
        return findById(id).mandatorySubjects
    }

    fun findBySemester(id: String, semester: Int): Collection<Subject> {
        return findById(id).mandatorySubjects.filter { it.semesterIndex == semester }
    }
}