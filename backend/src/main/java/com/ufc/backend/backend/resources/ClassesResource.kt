package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.services.ClassesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/classes"])
@CrossOrigin
class ClassesResource {
    @Autowired
    private val service: ClassesService? = null
    @GetMapping
    fun findAll(): ResponseEntity<List<Classes>> {
        return ResponseEntity.ok().body(service!!.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<Classes> {
        return ResponseEntity.ok().body(service!!.findById(id))
    }

    @GetMapping("/pre/{id}")
    fun findAllClassesThatHasTheGivenPreRequisite(@PathVariable id: String?): ResponseEntity<List<Classes>> {
        return ResponseEntity.ok().body(service!!.findAllClassesThatHasTheGivenPreRequisite(id))
    }

    @GetMapping("/feedback/{id}")
    fun findFeedbacks(@PathVariable id: String?): ResponseEntity<List<Feedback>> {
        return ResponseEntity.ok().body(
            service!!.findFeedbacks(id)
        )
    }
}