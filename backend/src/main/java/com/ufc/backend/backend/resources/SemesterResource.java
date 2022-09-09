package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/semester")
@CrossOrigin
public class SemesterResource {
    @Autowired
    private SemesterService service;

    @GetMapping("/{index}")
    public ResponseEntity<Semester> getSemesterByIndex(@PathVariable Integer index) {
        return ResponseEntity.ok().body(service.findSemesterByIndex(index));
    }

    @GetMapping
    public ResponseEntity<List<Semester>> findAllSemester() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createEmptySemester() {
        User user = service.createEmptySemester();
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(user.getId())
                .toUri()).body(user);
    }

    @PutMapping
    public ResponseEntity<User> updateSemester(@RequestBody Semester semester) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(semester.getIndex())
                .toUri()).body(service.updateSemester(semester));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Integer index) {
        service.deleteASemesterByIndex(index);
        return ResponseEntity.noContent().build();
    }
}
