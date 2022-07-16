package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CourseRepository extends MongoRepository<Course, String> {}
