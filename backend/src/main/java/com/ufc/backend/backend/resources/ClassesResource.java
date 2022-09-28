package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.feedback.Feedback;
import com.ufc.backend.backend.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/classes")
@CrossOrigin
public class ClassesResource {

    @Autowired
    private ClassesService service;

    @GetMapping
    public ResponseEntity<List<Classes>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/pre/{id}")
    public ResponseEntity<List<Classes>> findAllClassesThatHasTheGivenPreRequisite(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findAllClassesThatHasTheGivenPreRequisite(id));
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<List<Feedback>> findFeedbacks(@PathVariable String id){
        return ResponseEntity.ok().body(service.findFeedbacks(id));
    }
}
