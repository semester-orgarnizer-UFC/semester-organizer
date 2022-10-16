package com.ufc.backend.backend.model.based;

import com.ufc.backend.backend.model.feedback.Feedback;

import java.util.List;

public interface FeedbackBasedInterface {
void addFeedback(Feedback feedback);
List<Feedback> findAllFeedbacks();
void updateFeedback(Feedback feedback);
void deleteFeedback(Feedback feedback);
}
