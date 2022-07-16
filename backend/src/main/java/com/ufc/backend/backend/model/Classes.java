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
public class Classes {
    @Id
    private String id;
    private String name;
    /**
     * What classes you need to do before do this one in specific
     */
    private Set<Classes> preRequisites;
    /**
     * What classes you can do after doing this class
     */
    private Set<Classes> postRequisites;
}