package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends MongoRepository<Student, String> {
    @Transactional
    Student findByEmail(String email);
}
