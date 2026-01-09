package com.blogapp.Practice.exception;

public class BlogApiException extends RuntimeException{
    public BlogApiException(String msg){
        super(msg);
    }
}
