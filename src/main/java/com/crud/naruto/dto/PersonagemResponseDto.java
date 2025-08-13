package com.crud.naruto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PersonagemResponseDto {
    private String nome;
    private int idade;
    private String aldeia;
    private List<String> jutsus;
    private int chakra;
}
