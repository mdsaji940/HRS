package com.hrs.user.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not found on server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
