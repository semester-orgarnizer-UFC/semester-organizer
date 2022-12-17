package com.ufc.backend.backend.model.based

import com.ufc.backend.backend.model.Classes
import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
abstract class PersonBased : FeedbackBased() {
    @Id
    private val id: String? = null
    private val firstName: String? = null
    private val lastName: String? = null
    private val email: String? = null

    @DBRef
    private val classes: Set<Classes> = HashSet()
}