package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.model.student.Student
import com.ufc.backend.backend.model.student.StudentMapper
import com.ufc.backend.backend.model.student.dto.StudentDto
import com.ufc.backend.backend.model.student.dto.StudentInsertDto
import com.ufc.backend.backend.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/users"])
@CrossOrigin
class UserResource(
    private val service: UserService,
    private val mapper: StudentMapper

) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<Student> {
        return ResponseEntity.ok().body(service.findById(id))
    }

    @GetMapping("/subject/{id}")
    fun findClasses(@PathVariable id: String): ResponseEntity<Collection<Subject>> {
        return ResponseEntity.ok().body(service.findAllTakenClasses(id))
    }

    @PostMapping
    fun insert(@RequestBody user: StudentInsertDto): ResponseEntity<StudentDto> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/users")
                .buildAndExpand(user.email)
                .toUri()
        ).body(mapper.entityToDto(service.insert(user)))
    }
}