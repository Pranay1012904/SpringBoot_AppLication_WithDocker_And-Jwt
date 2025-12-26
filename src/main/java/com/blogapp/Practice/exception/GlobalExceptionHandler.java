package com.blogapp.Practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionObject> resourceNotFoundException(ResourceNotFoundException ex, WebRequest req){
            ExceptionDetails exceptionDetails=new ExceptionDetails(
                    LocalDateTime.now(),
                    ex.getMessage(),
                    req.getDescription(false),
                    "NO_SUCH_RESOURCE"
            );

            ExceptionObject exceptionObject=new ExceptionObject(
                    false,
                    List.of(exceptionDetails)
            );
            return new ResponseEntity<>(exceptionObject, HttpStatus.NOT_FOUND);
    }
}
