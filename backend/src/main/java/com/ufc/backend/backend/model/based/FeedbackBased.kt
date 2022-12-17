package com.ufc.backend.backend.model.based

import com.ufc.backend.backend.model.feedback.Feedback
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import org.springframework.data.mongodb.core.mapping.DBRef

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
abstract class FeedbackBased : FeedbackBasedInterface {
    @DBRef
    private val feedbacks: MutableList<Feedback> = ArrayList()
    override fun findAllFeedbacks(): MutableList<Feedback> {
        return feedbacks
    }

    override fun addFeedback(feedback: Feedback) {
        findAllFeedbacks().add(feedback)
    }

    override fun deleteFeedback(feedback: Feedback) {
        findAllFeedbacks().remove(feedback)
    }

    override fun updateFeedback(newFeedback: Feedback) {
        deleteFeedback(newFeedback)
        addFeedback(newFeedback)
    }
}