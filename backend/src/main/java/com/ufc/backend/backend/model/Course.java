package com.ufc.backend.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    @DBRef
    private List<Classes> mandatoryClasses;
    @DBRef
    private List<Classes> optionalClasses;
}