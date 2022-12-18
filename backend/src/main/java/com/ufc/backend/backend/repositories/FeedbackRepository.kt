package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.feedback.Feedback
import org.springframework.data.jpa.repository.JpaRepository

interface FeedbackRepository : JpaRepository<Feedback, String>