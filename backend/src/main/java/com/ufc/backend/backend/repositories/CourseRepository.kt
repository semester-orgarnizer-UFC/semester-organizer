package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, String>