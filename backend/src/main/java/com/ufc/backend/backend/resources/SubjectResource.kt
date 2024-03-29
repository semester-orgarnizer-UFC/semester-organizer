package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.model.subject.SubjectDto
import com.ufc.backend.backend.model.subject.SubjectMapper
import com.ufc.backend.backend.services.SubjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/subject"])
@CrossOrigin
class SubjectResource(
    private val service: SubjectService,
) {

    @GetMapping
    fun findAll(): ResponseEntity<Collection<SubjectDto>> {
        return ResponseEntity.ok().body(service.findAll().map { SubjectMapper.entityToDto(it) })
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