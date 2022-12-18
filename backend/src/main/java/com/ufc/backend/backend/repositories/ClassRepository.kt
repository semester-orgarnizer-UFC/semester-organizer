package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface ClassRepository : JpaRepository<Subject, String>