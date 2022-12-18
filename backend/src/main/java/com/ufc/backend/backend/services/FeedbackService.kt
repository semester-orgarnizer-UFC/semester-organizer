package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.repositories.FeedbackRepository
import org.springframework.stereotype.Service

@Service
class FeedbackService
    (
    private val repository: FeedbackRepository,
    private val userService: UserService,
    private val subjectService: SubjectService
) {

    fun findAll(): Collection<Feedback?> {
        return repository.findAll()
    }

    private fun findById(id: String): Feedback {
        return repository.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun save(feedback: Feedback, id: String): Feedback {
        return repository.save(feedback)
    }

    fun delete(id: String) {
        repository.deleteById(id)
    }
}