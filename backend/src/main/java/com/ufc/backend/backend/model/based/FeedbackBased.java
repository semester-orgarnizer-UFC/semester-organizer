package com.ufc.backend.backend.model.based;

import com.ufc.backend.backend.model.feedback.Feedback;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class FeedbackBased implements FeedbackBasedInterface {
    @DBRef
    private List<Feedback> feedbacks;

    @Override
    public List<Feedback> findAllFeedbacks() {
        return this.feedbacks;
    }

    @Override
    public void addFeedback(Feedback feedback) {
        if (this.feedbacks == null)
            setFeedbacks(List.of(feedback));
        else
            this.findAllFeedbacks().add(feedback);
    }

    @Override
    public void deleteFeedback(Feedback feedback) {
        this.findAllFeedbacks().removeIf(obj -> obj.getId().equals(feedback.getId()));
    }

    @Override
    public void updateFeedback(Feedback newFeedback) {
        deleteFeedback(newFeedback);
        addFeedback(newFeedback);
    }
}
