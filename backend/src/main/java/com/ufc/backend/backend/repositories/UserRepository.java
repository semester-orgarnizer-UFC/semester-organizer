package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
}