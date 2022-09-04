package com.ufc.backend.backend.resources.exceptions;

import com.ufc.backend.backend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<GenericError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(IdAlreadyExists.class)
    public ResponseEntity<GenericError> duplicateIdEntry(IdAlreadyExists e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(),
                "Duplicate entry", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(ClassDontHaveThePreRequisiteException.class)
    public ResponseEntity<GenericError> classDontHaveThePreRequisite(ClassDontHaveThePreRequisiteException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Don't have the pre requisite", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SemesterOutOfBoundsException.class)
    public ResponseEntity<GenericError> semesterOutOfBounds(SemesterOutOfBoundsException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Semester out of bounds", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ClassesAndPreRequisiteAtTheSameTimeException.class)
    public ResponseEntity<GenericError> classesAndPreRequisiteAtTheSameTimeException(ClassesAndPreRequisiteAtTheSameTimeException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(),
                "Class and pre requisite at the same time", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(ClassCantBeDoneAtTheFirstSemester.class)
    public ResponseEntity<GenericError> classCantBeDoneAtTheFirstSemester(ClassCantBeDoneAtTheFirstSemester e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Class can't be done at the first semester", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<GenericError> emailAlreadyExists(EmailAlreadyExists e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Email já existe", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class GenericError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
