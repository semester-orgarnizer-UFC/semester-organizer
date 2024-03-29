package com.ufc.backend.backend.services

import com.ufc.backend.backend.commons.model.SecurityUtils
import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.model.subject.Subject
import com.ufc.backend.backend.repositories.SubjectRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class SubjectService
    (
    private val repository: SubjectRepository,
) {

    fun findById(id: String): Subject {
        LOGGER.info("Searching class with the given id: $id")
        return repository.findById(id).orElseThrow { ObjectNotFoundException(id) }
    }

    fun save(subject: Subject) {
        repository.save(subject)
        LOGGER.info("Saving the given class in the database: $subject")
    }

    fun findAll(): Collection<Subject> {
        println(SecurityUtils.authenticatedUser)
        return repository.findAll()
    }

    fun findAllClassesThatHasTheGivenPreRequisite(preRequisiteId: String): Collection<Subject> {
        LOGGER.info("Finding subject that has the given pre-requisite: $preRequisiteId")
        return findAll().filter { it.preRequisite?.id == preRequisiteId }
    }

    fun findFeedbacks(id: String): Collection<Feedback> {
        LOGGER.info("Finding feedbacks of the given class: $id")
        return findById(id).feedbacks
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(SubjectService::class.java)
    }
}