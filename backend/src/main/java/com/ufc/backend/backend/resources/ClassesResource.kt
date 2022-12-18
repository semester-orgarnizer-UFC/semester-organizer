package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.Subject
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.services.ClassesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/subject"])
@CrossOrigin
class ClassesResource (
    private val service: ClassesService
){

    @GetMapping
    fun findAll(): ResponseEntity<Collection<Subject>> {
        return ResponseEntity.ok().body(service.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<Subject> {
        return ResponseEntity.ok().body(service.findById(id))
    }

    @GetMapping("/pre/{id}")
    fun findAllClassesThatHasTheGivenPreRequisite(@PathVariable id: String): ResponseEntity<Collection<Subject>> {
        return ResponseEntity.ok().body(service.findAllClassesThatHasTheGivenPreRequisite(id))
    }

    @GetMapping("/feedback/{id}")
    fun findFeedbacks(@PathVariable id: String): ResponseEntity<Collection<Feedback>> {
        return ResponseEntity.ok().body(
            service.findFeedbacks(id)
        )
    }
}