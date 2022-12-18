package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClassRepository : JpaRepository<Subject, String>