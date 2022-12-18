package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Semester
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SemesterRepository : JpaRepository<Semester, String>