package com.ufc.backend.backend.commons.model

import com.ufc.backend.backend.model.feedback.Feedback

interface FeedbackBased {
    val feedbacks: Collection<Feedback>
}