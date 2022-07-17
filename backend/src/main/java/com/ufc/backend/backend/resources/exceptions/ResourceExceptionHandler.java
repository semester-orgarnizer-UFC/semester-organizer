package com.ufc.backend.backend.resources.exceptions;

import com.ufc.backend.backend.exceptions.IdAlreadyExists;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
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
