package com.ufc.backend.backend.model.feedback;

import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    private String id;
    private String commentary;
    private Rating rating;
    private Classes classes;
    private User user;
    private boolean isAnonymous;

    public User getUser() {
        return this.isAnonymous ? null : this.user;
    }
}
