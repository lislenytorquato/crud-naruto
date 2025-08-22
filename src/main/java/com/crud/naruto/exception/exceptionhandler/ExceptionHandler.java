package com.crud.naruto.exception.exceptionhandler;

import com.crud.naruto.exception.JutsuNaoEncontradoException;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(PersonagemNaoEncontradoException.class)
    public void personagemNaoEncontradoExceptionHandler(){

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(JutsuNaoEncontradoException.class)
    public void jutsuNaoEncontradoExceptionHandler(){
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>  personagemRequestDtoValidationHandler(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(err ->{
            String field = err.getField();
            String message = err.getDefaultMessage();
            errors.put(field,message);
        });

        return ResponseEntity.badRequest().body(errors);
    }

}