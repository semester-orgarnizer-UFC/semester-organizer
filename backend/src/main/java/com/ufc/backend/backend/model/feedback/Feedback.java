package com.ufc.backend.backend.model.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    private String id;
    private String commentary;
    private Rating rating;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Classes classes;
    private User user;
    @JsonIgnore
    private User actualUser;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isAnonymous;
}
