package com.hrs.hotel.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String s){
        super(s);
    }

    public ResourceNotFoundException(){
        super("Resource not found");
    }
}
