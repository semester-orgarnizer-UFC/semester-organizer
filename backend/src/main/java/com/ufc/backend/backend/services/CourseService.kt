package com.ufc.backend.backend.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Course
import com.ufc.backend.backend.repositories.ClassRepository
import com.ufc.backend.backend.repositories.CourseRepository
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val classRepository: ClassRepository
) {

    fun populateCourses() {
        try {
            val file = File(Objects.requireNonNull(this.javaClass.classLoader.getResource("courses/cc.json")).file)
            val mapper = ObjectMapper()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            val course = mapper.readValue(file, Course::class.java)
            classRepository.saveAll(course.mandatoryClasses)
            courseRepository.save(mapper.readValue(file, Course::class.java))
        } catch (e: Exception) {
            throw e
        }
    }

    fun findById(id: String): Course {
        return courseRepository.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun findAll(): List<Course> {
        return courseRepository.findAll()
    }

    fun findMandatory(id: String): Collection<Classes> {
        return findById(id).mandatoryClasses
    }

    fun findOptional(id: String): Collection<Classes> {
        return findById(id).optionalClasses
    }

    fun findBySemester(id: String, semester: Int): List<Classes> {
        return findById(id).mandatoryClasses.filter { it.semester == semester }
    }
}