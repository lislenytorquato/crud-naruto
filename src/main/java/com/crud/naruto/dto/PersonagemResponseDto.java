package com.crud.naruto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PersonagemResponseDto {
    private String nome;
    private List<JutsuDto> jutsus;
    private int chakra;
    private int vida;
}
