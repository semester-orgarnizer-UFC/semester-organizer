package com.ufc.backend.backend.repositories;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.feedback.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}
