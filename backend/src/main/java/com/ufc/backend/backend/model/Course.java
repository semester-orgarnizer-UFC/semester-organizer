package com.ufc.backend.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Document
public class Course {
    @Id
    private String id;
    private String name;
    private Set<Classes> mandatoryClasses;
    private Set<Classes> optionalClasses;
}