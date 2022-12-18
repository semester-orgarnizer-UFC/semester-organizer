package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.subject.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository : JpaRepository<Subject, String>