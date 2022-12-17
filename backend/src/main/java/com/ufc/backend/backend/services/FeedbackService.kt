package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.ForbiddenException
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.repositories.FeedbackRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FeedbackService {
    @Autowired
    private val repository: FeedbackRepository? = null

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val classesService: ClassesService? = null
    fun findAll(): List<Feedback?> {
        return repository!!.findAll()
    }

    fun findById(id: String): Feedback {
        return repository!!.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun save(feedback: Feedback): Feedback {
        feedback.setActualUser(userService!!.findById(AuthService.userAuthenticated().id))
        feedback.setUser(if (feedback.isAnonymous()) null else feedback.getActualUser())
        repository!!.save(feedback)
        val classes = classesService!!.findById(feedback.getClasses().getId())
        classes!!.addFeedback(feedback)
        classesService.save(classes)
        feedback.setClasses(classes)
        return repository.save(feedback)
    }

    fun delete(id: String) {
        val feedback = findById(id)
        if (feedback.getUser() !== userService!!.findById(AuthService.userAuthenticated().id)) {
            throw ForbiddenException()
        }
        feedback.getUser().deleteFeedback(feedback)
        repository!!.delete(feedback)
    }

    fun update(feedback: Feedback): Feedback {
        feedback.setActualUser(userService!!.findById(AuthService.userAuthenticated().id))
        feedback.setUser(if (feedback.isAnonymous()) null else feedback.getActualUser())
        repository!!.save(feedback)
        val classes = classesService!!.findById(feedback.getClasses().getId())
        classes!!.updateFeedback(feedback)
        classesService.save(classes)
        feedback.setClasses(classes)
        return repository.save(feedback)
    }
}