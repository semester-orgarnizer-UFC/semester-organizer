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
class FeedbackResource {
    @Autowired
    private val service: FeedbackService? = null
    @PostMapping
    fun insert(@RequestBody feedback: Feedback): ResponseEntity<Feedback> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/feedback")
                .buildAndExpand(feedback.getId())
                .toUri()
        ).body(service!!.save(feedback))
    }

    @PutMapping
    fun update(@RequestBody feedback: Feedback?): ResponseEntity<Feedback> {
        return ResponseEntity.ok().body(service!!.update(feedback))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String?): ResponseEntity<Void> {
        service!!.delete(id)
        return ResponseEntity.ok().build()
    }
}