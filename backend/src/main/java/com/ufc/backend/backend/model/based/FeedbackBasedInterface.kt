package com.ufc.backend.backend.model.based

import com.ufc.backend.backend.model.feedback.Feedback

interface FeedbackBasedInterface {
    fun addFeedback(feedback: Feedback)
    fun findAllFeedbacks(): MutableList<Feedback>
    fun updateFeedback(feedback: Feedback)
    fun deleteFeedback(feedback: Feedback)
}