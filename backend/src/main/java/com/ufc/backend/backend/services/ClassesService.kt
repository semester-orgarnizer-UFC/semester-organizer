package com.ufc.backend.backend.services

import com.ufc.backend.backend.exceptions.ObjectNotFoundException
import com.ufc.backend.backend.model.Classes
import com.ufc.backend.backend.model.feedback.Feedback
import com.ufc.backend.backend.repositories.ClassRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ClassesService {
    @Autowired
    private val repository: ClassRepository? = null
    fun findById(id: String): Classes {
        LOGGER.info("Searching class with the given id: $id")
        return repository!!.findById(id).orElseThrow { ObjectNotFoundException(id) }!!
    }

    fun save(classes: Classes?) {
        repository!!.save<Classes>(classes)
        LOGGER.info("Saving the given class in the database: $classes")
    }

    @Cacheable("classes")
    fun findAll(): List<Classes?> {
        LOGGER.info("Finding all classes")
        return repository!!.findAll()
    }

    fun findAllClassesThatHasTheGivenPreRequisite(preRequisiteId: String): List<Classes?> {
        LOGGER.info("Finding classes that has the given pre-requisite: $preRequisiteId")
        return findAll().stream().filter { obj: Classes? ->
            obj.getPreRequisite() != null && obj.getPreRequisite().getId().equals(preRequisiteId)
        }
            .collect(Collectors.toList())
    }

    fun findFeedbacks(id: String): List<Feedback> {
        LOGGER.info("Finding feedbacks of the given class: $id")
        return findById(id).findAllFeedbacks()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ClassesService::class.java)
    }
}