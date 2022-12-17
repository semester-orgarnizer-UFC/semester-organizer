package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.feedback.Feedback
import org.springframework.data.mongodb.repository.MongoRepository

interface FeedbackRepository : MongoRepository<Feedback?, String?>