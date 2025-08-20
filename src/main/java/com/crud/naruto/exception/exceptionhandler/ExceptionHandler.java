package com.crud.naruto.exception.exceptionhandler;

import com.crud.naruto.exception.JutsuNaoEncontradoException;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

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

}