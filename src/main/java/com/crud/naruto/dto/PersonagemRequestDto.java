package com.crud.naruto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class PersonagemRequestDto {
    @NotBlank(message = "nome em branco, nulo ou vazio ")
    private String nome;
    @NotEmpty(message =" jutsus vazio ou nulo" )
    private Map<String, JutsuDto> jutsu;
    private int vida;
}
