package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.Classes
import org.springframework.data.mongodb.repository.MongoRepository

interface ClassRepository : MongoRepository<Classes?, String?>