package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Course
import org.springframework.data.mongodb.repository.MongoRepository

interface CourseRepository : MongoRepository<Course?, String?>