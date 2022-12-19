package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.course.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : JpaRepository<Course, String>