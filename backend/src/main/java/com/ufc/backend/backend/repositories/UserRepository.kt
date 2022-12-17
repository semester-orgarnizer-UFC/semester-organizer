package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.transaction.annotation.Transactional

interface UserRepository : MongoRepository<Student?, String> {
    @Transactional
    fun findByEmail(email: String?): Student?
}