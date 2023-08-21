package com.planner.conference.exception;

import com.planner.conference.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCodes.ENTITY_NOT_FOUND);
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setDate(new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
