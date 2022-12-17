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
    private val classesService: ClassesService
) {

    fun findAll(): Collection<Feedback?> {
        return repository.findAll()
    }

    private fun findById(id: String): Feedback {
        return repository.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun save(feedback: Feedback, id: String): Feedback {
        feedback.actualUser = userService.findById(id)
        feedback.user = feedback.actualUser!!
        repository.save(feedback)
        val classes = classesService.findById(feedback.classes.id)
        classes.addFeedback(feedback)
        classesService.save(classes)
        feedback.classes = classes
        return repository.save(feedback)
    }

    fun delete(id: String) {
        val feedback = findById(id)
        feedback.user.deleteFeedback(feedback)
        repository.delete(feedback)
    }
}