package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.Student
import com.ufc.backend.backend.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/users"])
@CrossOrigin
class UserResource(
    private val service: UserService

) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<Student> {
        return ResponseEntity.ok().body(service.findById(id))
    }

    @GetMapping("/classes/{id}")
    fun findClasses(@PathVariable id: String): ResponseEntity<Collection<Classes>> {
        return ResponseEntity.ok().body(service.findAllTakenClasses(id))
    }

    @GetMapping("/classes/nottaken/{id}")
    fun findClassesNotTaken(@PathVariable id: String): ResponseEntity<Collection<Classes>> {
        return ResponseEntity.ok().body(service.findAllNotTakenClasses(id))
    }

    @PostMapping
    fun insert(@RequestBody user: Student): ResponseEntity<Student> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/users")
                .buildAndExpand(user.person.id)
                .toUri()
        ).body(service.insert(user))
    }
/*  @DeleteMapping("/{classId}")
    public ResponseEntity<Boolean> deleteClassFromSemester(@PathVariable String classId) {
        service.deleteClassFromSemester(classId, null);
        return ResponseEntity.noContent().build();
    }*/
}