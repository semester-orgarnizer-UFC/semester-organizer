package com.ufc.backend.backend.resources

import com.ufc.backend.backend.model.Semester
import com.ufc.backend.backend.model.Student
import com.ufc.backend.backend.services.SemesterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/semester"])
@CrossOrigin
class SemesterResource {
    @Autowired
    private val service: SemesterService? = null
    @GetMapping("/{index}")
    fun getSemesterByIndex(@PathVariable index: Int?): ResponseEntity<Semester> {
        return ResponseEntity.ok().body(service!!.findSemesterByIndex(index))
    }

    @GetMapping
    fun findAllSemester(): ResponseEntity<List<Semester>> {
        return ResponseEntity.ok().body(service!!.findAll())
    }

    @PostMapping
    fun createEmptySemester(): ResponseEntity<Student> {
        val user = service!!.createEmptySemester()
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(user.getId())
                .toUri()
        ).body(user)
    }

    @PutMapping
    fun updateSemester(@RequestBody semester: Semester): ResponseEntity<Student> {
        return ResponseEntity.created(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(semester.getIndex())
                .toUri()
        ).body(service!!.updateSemester(semester))
    }

    @DeleteMapping("/{index}")
    fun deleteSemester(@PathVariable index: Int?): ResponseEntity<Void> {
        service!!.deleteASemesterByIndex(index)
        return ResponseEntity.noContent().build()
    }
}