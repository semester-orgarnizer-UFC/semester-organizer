package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin
public class ClassesResource {

    @Autowired
    private ClassesService service;

    @PostMapping("/class/create")
    public ResponseEntity<Classes> insert(@RequestBody Classes obj) {
        return ResponseEntity.ok().body(service.insert(obj));
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Classes>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<Classes> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
