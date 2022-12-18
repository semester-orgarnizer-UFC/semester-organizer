package com.ufc.backend.backend.resources.exceptions

import com.ufc.backend.backend.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException::class)
    fun objectNotFound(e: ObjectNotFoundException, request: HttpServletRequest): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Not found", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err)
    }

    @ExceptionHandler(IdAlreadyExists::class)
    fun duplicateIdEntry(e: IdAlreadyExists, request: HttpServletRequest): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.CONFLICT.value(),
                "Duplicate entry", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err)
    }

    @ExceptionHandler(ClassDontHaveThePreRequisiteException::class)
    fun classDontHaveThePreRequisite(
        e: ClassDontHaveThePreRequisiteException,
        request: HttpServletRequest
    ): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Don't have the pre requisite", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }

    @ExceptionHandler(SemesterOutOfBoundsException::class)
    fun semesterOutOfBounds(
        e: SemesterOutOfBoundsException,
        request: HttpServletRequest
    ): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Semester out of bounds", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }

    @ExceptionHandler(ClassesAndPreRequisiteAtTheSameTimeException::class)
    fun classesAndPreRequisiteAtTheSameTimeException(
        e: ClassesAndPreRequisiteAtTheSameTimeException,
        request: HttpServletRequest
    ): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.CONFLICT.value(),
                "Class and pre requisite at the same time", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err)
    }

    @ExceptionHandler(ClassCantBeDoneAtTheFirstSemester::class)
    fun classCantBeDoneAtTheFirstSemester(
        e: ClassCantBeDoneAtTheFirstSemester,
        request: HttpServletRequest
    ): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Class can't be done at the first semester", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }

    @ExceptionHandler(EmailAlreadyExists::class)
    fun emailAlreadyExists(e: EmailAlreadyExists, request: HttpServletRequest): ResponseEntity<GenericError> {
        val err = e.message?.let {
            GenericError(
                System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Email já existe", it, request.requestURI
            )
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err)
    }

    /*@ExceptionHandler(ForbiddenException::class)
    fun forbiddenException(e: ForbiddenException, request: HttpServletRequest): ResponseEntity<GenericError> {
        val err = GenericError(
            System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
            "Nao autorizado", e.message, request.requestURI
        )
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err)
    }*/
}
