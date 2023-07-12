package com.senior.devrecruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotCompatibleException extends RuntimeException{

    public ResponseEntity notCompatibleException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O framework não é compatível com a linguagem");
    }
}
