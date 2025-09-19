package com.crud.naruto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class PersonagemRequestDto {
    @NotBlank(message = "nome em branco, nulo ou vazio ")
    private String nome;
    private List<JutsuDto> jutsus;
    private int vida;
}
