package com.ufc.backend.backend.commons.model

import com.ufc.backend.backend.model.feedback.Feedback

interface FeedbackBasedInterface {
    fun addFeedback(feedback: Feedback)
    fun findAllFeedbacks(): MutableSet<Feedback>
    fun updateFeedback(feedback: Feedback)
    fun deleteFeedback(feedback: Feedback)
}