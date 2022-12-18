package com.ufc.backend.backend.commons.model

import com.ufc.backend.backend.model.feedback.Feedback

open class FeedbackBased : FeedbackBasedInterface {
    val feedbacks: MutableSet<Feedback> = mutableSetOf()
    override fun findAllFeedbacks(): MutableSet<Feedback> {
        return feedbacks
    }

    override fun addFeedback(feedback: Feedback) {
        findAllFeedbacks().add(feedback)
    }

    override fun deleteFeedback(feedback: Feedback) {
        findAllFeedbacks().remove(feedback)
    }

    override fun updateFeedback(feedback: Feedback) {
        deleteFeedback(feedback)
        addFeedback(feedback)
    }
}