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

    @GetMapping("/{index}")
    public ResponseEntity<Semester> getSemesterByIndex(@PathVariable Integer index) {
        return ResponseEntity.ok().body(service.findSemesterByIndex(index));
    }

    @PostMapping
    public ResponseEntity<User> createSemester(@RequestBody Semester semester) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(semester.getIndex())
                .toUri()).body(service.createOrUpdateSemester(semester));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Integer index) {
        service.deleteASemesterByIndex(index);
        return ResponseEntity.noContent().build();
    }
}
