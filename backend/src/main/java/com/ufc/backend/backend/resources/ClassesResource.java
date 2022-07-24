package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class ClassesResource {

    @Autowired
    private ClassesService service;

    @GetMapping("/classes")
    public ResponseEntity<List<Classes>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<Classes> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/classes/pre/{id}")
    public ResponseEntity<Classes> preRequisite(@PathVariable String id) {
        return ResponseEntity.ok().body(service.preRequisite(id));
    }
}
