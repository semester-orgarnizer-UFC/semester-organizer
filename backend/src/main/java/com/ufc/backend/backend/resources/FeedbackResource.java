package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.feedback.Feedback;
import com.ufc.backend.backend.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/feedback")
@CrossOrigin
public class FeedbackResource {

    @Autowired
    private FeedbackService service;

    @PostMapping
    public ResponseEntity<Feedback> insert(@RequestBody Feedback feedback) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/feedback")
                .buildAndExpand(feedback.getId())
                .toUri()).body(service.save(feedback));
    }

    @PutMapping
    public ResponseEntity<Feedback> update(@RequestBody Feedback feedback) {
        return ResponseEntity.ok().body(service.update(feedback));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }



}
