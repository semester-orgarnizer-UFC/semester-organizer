package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Semester;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/semester")
@CrossOrigin
public class SemesterResource {
    @Autowired
    private SemesterService service;

    @GetMapping("/{id}/{index}")
    public ResponseEntity<Semester> getSemesterByIndex(@PathVariable String id, @PathVariable Integer index) {
        return ResponseEntity.ok().body(service.findSemesterByIndex(id, index));
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> createSemester(@PathVariable String id, @RequestBody Semester semester) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(semester.getIndex())
                .toUri()).body(service.createOrUpdateSemester(id, semester));
    }

    @DeleteMapping("/{id}/{index}")
    public ResponseEntity<Void> deleteSemester(@PathVariable String id, @PathVariable Integer index) {
        service.deleteASemesterByIndex(id, index);
        return ResponseEntity.noContent().build();
    }
}
