package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CourseRepository extends MongoRepository<Course, UUID> {
}