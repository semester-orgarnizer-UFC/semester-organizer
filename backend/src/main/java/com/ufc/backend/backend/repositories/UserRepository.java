package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends MongoRepository<User, String> {
    @Transactional
    User findByEmail(String email);
}
