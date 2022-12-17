package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.repositories.ClassRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ClassesService
    (
    private val repository: ClassRepository
) {
    fun findById(id: String): Classes {
        LOGGER.info("Searching class with the given id: $id")
        return repository.findById(id).orElseThrow { ObjectNotFoundException(id) }
    }

    fun save(classes: Classes) {
        repository.save(classes)
        LOGGER.info("Saving the given class in the database: $classes")
    }

    @Cacheable("classes")
    fun findAll(): List<Classes?> {
        LOGGER.info("Finding all classes")
        return repository.findAll()
    }

    fun findAllClassesThatHasTheGivenPreRequisite(preRequisiteId: String): Collection<Classes?> {
        LOGGER.info("Finding classes that has the given pre-requisite: $preRequisiteId")
        return findAll().filter { it?.preRequisite?.id ==  preRequisiteId}
    }

    fun findFeedbacks(id: String): Collection<Feedback> {
        LOGGER.info("Finding feedbacks of the given class: $id")
        return findById(id).findAllFeedbacks()
    }
    companion object {
        private val LOGGER = LoggerFactory.getLogger(ClassesService::class.java)
    }
}