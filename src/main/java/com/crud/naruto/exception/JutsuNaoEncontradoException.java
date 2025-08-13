package com.crud.naruto.exception;

public class PersonagemNaoEncontradoException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Personagem não encontrado";
    }
}
