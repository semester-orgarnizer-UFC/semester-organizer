package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.feedback.Feedback;
import com.ufc.backend.backend.services.ClassesService;
import com.ufc.backend.backend.services.FeedbackService;
import com.ufc.backend.backend.services.StatsService;
import com.ufc.backend.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stats")
@CrossOrigin
public class StatsResource {

    @Autowired
    private StatsService service;

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/users")
    public ResponseEntity<Integer> numberOfUsers() {
        return ResponseEntity.ok().body(userService.findAll().size());
    }

    @GetMapping("/semesters")
    public ResponseEntity<Integer> numberOfSemester() {
        return ResponseEntity.ok().body(service.numberOfSemesters());
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<Integer> numberOfFeedbacks(){
        return ResponseEntity.ok().body(feedbackService.findAll().size());
    }
}
