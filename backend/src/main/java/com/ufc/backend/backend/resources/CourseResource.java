package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Course;
import com.ufc.backend.backend.services.ClassesService;
import com.ufc.backend.backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class CourseResource {

    @Autowired
    private CourseService service;

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/courses/mandatory/{id}")
    public ResponseEntity<List<Classes>> findMandatory(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findMandatory(id));
    }

    @GetMapping("/courses/optional/{id}")
    public ResponseEntity<List<Classes>> findOptional(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findOptional(id));
    }

}
