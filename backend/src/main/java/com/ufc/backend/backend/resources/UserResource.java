package com.ufc.backend.backend.resources;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserResource {

    @Autowired
    private UserService service;


    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/classes")
    public ResponseEntity<List<Classes>> findClasses() {
        return ResponseEntity.ok().body(service.findAllTakenClasses());
    }
    @GetMapping("/classes/nottaken/")
    public ResponseEntity<List<Classes>> findClassesNotTaken() {
        return ResponseEntity.ok().body(service.findAllNotTakenClasses());
    }


    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/users")
                .buildAndExpand(user.getId())
                .toUri()).body(service.insert(user));
    }

  /*  @DeleteMapping("/{classId}")
    public ResponseEntity<Boolean> deleteClassFromSemester(@PathVariable String classId) {
        service.deleteClassFromSemester(classId, null);
        return ResponseEntity.noContent().build();
    }*/
}
