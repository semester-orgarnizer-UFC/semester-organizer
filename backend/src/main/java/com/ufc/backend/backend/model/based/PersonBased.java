package com.ufc.backend.backend.model.based;

import com.ufc.backend.backend.model.Classes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class PersonBased extends FeedbackBased {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @DBRef
    private Set<Classes> classes = new HashSet<>();
}
