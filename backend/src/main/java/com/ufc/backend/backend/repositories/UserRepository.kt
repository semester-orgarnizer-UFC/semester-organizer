package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

interface UserRepository : JpaRepository<Student, String> {
    @Transactional
    fun findByPersonEmail(email: String): Student?
}