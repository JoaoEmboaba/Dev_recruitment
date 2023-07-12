package com.senior.devrecruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The object was not found")
public class NotFoundException extends RuntimeException{
    private static final Long serialVersionUUID = 1L;

    public NotFoundException(){
        super("The object was not found");
    }
}
