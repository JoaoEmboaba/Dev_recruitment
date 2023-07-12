
package com.senior.devrecruitment.exception;
import org.springframework.validation.FieldError;

record DTOErroValidacao(String campo, String mensagem) {
    public DTOErroValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
