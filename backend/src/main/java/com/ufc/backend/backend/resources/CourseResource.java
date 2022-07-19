package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Course;
import com.ufc.backend.backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
@CrossOrigin
public class CourseResource {

    @Autowired
    private CourseService service;

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/mandatory/{id}")
    public ResponseEntity<List<Classes>> findMandatory(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findMandatory(id));
    }

    @GetMapping("/optional/{id}")
    public ResponseEntity<List<Classes>> findOptional(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findOptional(id));
    }

    @GetMapping("/mandatory/{id}/{semester}")
    public ResponseEntity<List<Classes>> findBySemester(@PathVariable String id, @PathVariable Integer semester) {
        return ResponseEntity.ok().body(service.findBySemester(id, semester));
    }

}
