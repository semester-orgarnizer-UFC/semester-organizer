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
class UserResource {
    @Autowired
    private val service: UserService? = null
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<Student> {
        return ResponseEntity.ok().body(service!!.findById(id))
    }

    @GetMapping("/classes")
    fun findClasses(): ResponseEntity<Set<Classes>> {
        return ResponseEntity.ok().body(service!!.findAllTakenClasses())
    }

    @GetMapping("/classes/nottaken/")
    fun findClassesNotTaken(): ResponseEntity<Set<Classes>> {
        return ResponseEntity.ok().body(service!!.findAllNotTakenClasses())
    }

    @PostMapping
    fun insert(@RequestBody user: Student): ResponseEntity<Student> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/users")
                .buildAndExpand(user.getId())
                .toUri()
        ).body(service!!.insert(user))
    } /*  @DeleteMapping("/{classId}")
    public ResponseEntity<Boolean> deleteClassFromSemester(@PathVariable String classId) {
        service.deleteClassFromSemester(classId, null);
        return ResponseEntity.noContent().build();
    }*/
}