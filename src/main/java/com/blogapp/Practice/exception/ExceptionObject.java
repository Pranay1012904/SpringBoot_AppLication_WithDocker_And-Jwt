package com.blogapp.Practice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ExceptionObject {

    public ExceptionObject(boolean success, List<ExceptionDetails> exceptionList) {
        this.success = success;
        this.exceptionList = exceptionList;
    }

    boolean success;
    private List<ExceptionDetails> exceptionList;
}
