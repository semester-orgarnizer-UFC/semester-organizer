package com.ufc.backend.backend.model;

import com.ufc.backend.backend.model.based.FeedbackBased;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Classes extends FeedbackBased {
    @Id
    private String id;
    private String name;
    @DBRef
    private Classes preRequisite;
    private Integer hours;
    private Integer semester;
    @DBRef
    private Set<Teacher> teachers = new HashSet<>();

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.getId().equals(((Classes) obj).getId());
    }
}