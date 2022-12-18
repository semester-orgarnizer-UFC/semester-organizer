package com.ufc.backend.backend.resources

import com.ufc.backend.backend.services.FeedbackService
import com.ufc.backend.backend.services.StatsService
import com.ufc.backend.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/stats"])
@CrossOrigin
class StatsResource {
   /* @Autowired
    private val service: StatsService? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val feedbackService: FeedbackService? = null
    @GetMapping("/users")
    fun numberOfUsers(): ResponseEntity<Int> {
        return ResponseEntity.ok().body(userService!!.findAll().size)
    }

    @GetMapping("/semesters")
    fun numberOfSemester(): ResponseEntity<Int> {
        return ResponseEntity.ok().body(service!!.numberOfSemesters())
    }

    @GetMapping("/feedbacks")
    fun numberOfFeedbacks(): ResponseEntity<Int> {
        return ResponseEntity.ok().body(feedbackService!!.findAll().size)
    }*/
}