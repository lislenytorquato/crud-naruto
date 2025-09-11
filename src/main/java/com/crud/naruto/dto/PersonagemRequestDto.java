package com.crud.naruto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PersonagemRequestDto {
    @NotBlank(message = "nome em branco, nulo ou vazio ")
    private String nome;
    @NotEmpty(message =" jutsus vazio ou nulo" )
    private List<JutsuDto> jutsus;
    private int vida;
}
