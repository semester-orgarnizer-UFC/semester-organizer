package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.Classes;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ClassRepository extends MongoRepository<Classes, UUID> {
}