package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.course.CourseDto
import com.ufc.backend.backend.model.course.CourseMapper
import com.ufc.backend.backend.services.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/courses"])
@CrossOrigin
class CourseResource(
    private val service: CourseService,
    private val mapper: CourseMapper
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<CourseDto> {
        return ResponseEntity.ok().body(mapper.entityToDto(service.findById(id)))
    }

    @GetMapping
    fun findAll(): ResponseEntity<Collection<CourseDto>> {
        return ResponseEntity.ok().body(service.findAll().map { mapper.entityToDto(it) })
    }

    @GetMapping("/mandatory/{id}")
    fun findMandatory(@PathVariable id: String): ResponseEntity<Collection<Subject>> {
        return ResponseEntity.ok().body(service.findMandatory(id))
    }


    @GetMapping("/mandatory/{id}/{semester}")
    fun findBySemester(@PathVariable id: String, @PathVariable semester: Int): ResponseEntity<Collection<Subject>> {
        return ResponseEntity.ok().body(service.findBySemester(id, semester))
    }
}