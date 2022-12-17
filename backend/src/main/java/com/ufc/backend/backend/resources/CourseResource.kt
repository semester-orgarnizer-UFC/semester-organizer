package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Course
import com.ufc.backend.backend.services.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/courses"])
@CrossOrigin
class CourseResource {
    @Autowired
    private val service: CourseService? = null
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<Course> {
        return ResponseEntity.ok().body(service!!.findById(id))
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Course>> {
        return ResponseEntity.ok().body(service!!.findAll())
    }

    @GetMapping("/mandatory/{id}")
    fun findMandatory(@PathVariable id: String?): ResponseEntity<Set<Classes>> {
        return ResponseEntity.ok().body(service!!.findMandatory(id))
    }

    @GetMapping("/optional/{id}")
    fun findOptional(@PathVariable id: String?): ResponseEntity<Set<Classes>> {
        return ResponseEntity.ok().body(service!!.findOptional(id))
    }

    @GetMapping("/mandatory/{id}/{semester}")
    fun findBySemester(@PathVariable id: String?, @PathVariable semester: Int?): ResponseEntity<List<Classes>> {
        return ResponseEntity.ok().body(service!!.findBySemester(id, semester))
    }
}