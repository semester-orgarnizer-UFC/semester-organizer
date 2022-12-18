package com.ufc.backend.backend.repositories

import com.ufc.backend.backend.model.feedback.Feedback
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface FeedbackRepository : JpaRepository<Feedback, String>