package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/semester")
@CrossOrigin
public class SemesterResource {
    @Autowired
    private UserService service;

    @GetMapping("/{id}/{index}")
    public ResponseEntity<Semester> getSemesterByIndex(@PathVariable String id, @PathVariable Integer index) {
        return ResponseEntity.ok().body(service.getSemesterByIndex(id, index));
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> insertSemester(@PathVariable String id, @RequestBody Semester semester) {
        return ResponseEntity.ok().body(service.insertSemester(id, semester));
    }
}
