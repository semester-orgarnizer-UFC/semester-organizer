package com.ufc.backend.backend.services

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.ufc.backend.backend.exceptions.CouldNotLoadTheClassesException
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Course
import com.ufc.backend.backend.repositories.ClassRepository
import com.ufc.backend.backend.repositories.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import java.util.stream.Collectors

@Service
class CourseService {
    @Autowired
    private val courseRepository: CourseRepository? = null

    @Autowired
    private val classRepository: ClassRepository? = null
    fun populateCourses() {
        try {
            val `in` = File(Objects.requireNonNull(this.javaClass.classLoader.getResource("courses/cc.json")).file)
            val mapper = ObjectMapper()
            // Necessary to not fail when accessing the properties : ref, id. for MongoDB relation
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            val course = mapper.readValue(`in`, Course::class.java)
            classRepository!!.saveAll(course.getMandatoryClasses())
            courseRepository!!.save(mapper.readValue(`in`, Course::class.java))
        } catch (e: Exception) {
            throw CouldNotLoadTheClassesException(e.message, e.cause)
        }
    }

    fun findById(id: String): Course {
        return courseRepository!!.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun findAll(): List<Course?> {
        return courseRepository!!.findAll()
    }

    fun findMandatory(id: String): Set<Classes> {
        return findById(id).getMandatoryClasses()
    }

    fun findOptional(id: String): Set<Classes> {
        return findById(id).getOptionalClasses()
    }

    fun findBySemester(id: String, semester: Int?): List<Classes> {
        return findById(id).getMandatoryClasses().stream().filter { obj -> obj.getSemester() == semester }
            .collect(Collectors.toList())
    }
}