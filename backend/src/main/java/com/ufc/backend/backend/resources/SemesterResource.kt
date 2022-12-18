package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.student.Student
import com.ufc.backend.backend.services.SemesterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/semester"])
@CrossOrigin
class SemesterResource(
    private val service: SemesterService
) {
    @GetMapping("/{index}/{id}")
    fun getSemesterByIndex(@PathVariable index: Int, @PathVariable id: String): ResponseEntity<Semester> {
        return ResponseEntity.ok().body(service.findSemesterByIndex(index, id))
    }

    @GetMapping("/{id}")
    fun findAllSemester(@PathVariable id: String): ResponseEntity<Collection<Semester>> {
        return ResponseEntity.ok().body(service.findAll(id))
    }

    @PostMapping("/{id}")
    fun createEmptySemester(id: String): ResponseEntity<Student> {
        val user = service.createEmptySemester(id)
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(id)
                .toUri()
        ).body(user)
    }

    @PutMapping("/{id}")
    fun updateSemester(@RequestBody semester: Semester, id: String): ResponseEntity<Student> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(semester.semesterIndex)
                .toUri()
        ).body(service.updateSemester(semester, id))
    }

    @DeleteMapping("/{index}/{id}")
    fun deleteSemester(@PathVariable index: Int, @PathVariable id: String): ResponseEntity<Void> {
        service.deleteASemesterByIndex(index, id)
        return ResponseEntity.noContent().build()
    }
}