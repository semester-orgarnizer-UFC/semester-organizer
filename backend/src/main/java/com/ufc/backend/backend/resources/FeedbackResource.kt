package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.services.FeedbackService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/feedback"])
@CrossOrigin
class FeedbackResource(
    private val service: FeedbackService
) {
    @PostMapping("/{id}")
    fun insert(@RequestBody feedback: Feedback, @PathVariable id: String): ResponseEntity<Feedback> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/feedback")
                .buildAndExpand(feedback.id)
                .toUri()
        ).body(service.save(feedback, id))
    }

    @PutMapping("/{id}")
    fun update(@RequestBody feedback: Feedback, @PathVariable id: String): ResponseEntity<Feedback> {
        return ResponseEntity.ok().body(service.save(feedback, id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.ok().build()
    }
}