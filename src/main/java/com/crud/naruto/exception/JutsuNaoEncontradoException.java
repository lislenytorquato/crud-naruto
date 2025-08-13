package com.crud.naruto.exception;

public class JutsuNaoEncontradoException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Jutsu não encontrado";
    }
}
