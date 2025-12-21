package com.blogapp.Practice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ExceptionDetails {

    public ExceptionDetails(LocalDateTime timestamp, String message, String exceptionDescription, String exceptionCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.exceptionDescription = exceptionDescription;
        this.exceptionCode = exceptionCode;
    }

    private LocalDateTime timestamp;
    private String message;
    private String exceptionDescription;
    private String exceptionCode;
}
