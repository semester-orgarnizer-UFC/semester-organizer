package com.ufc.backend.backend.model.based;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.feedback.Feedback;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class PersonBased extends FeedbackBased{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @DBRef
    private List<Classes> classes;
}
