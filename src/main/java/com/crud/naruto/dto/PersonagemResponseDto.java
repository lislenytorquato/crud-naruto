package com.crud.naruto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class PersonagemResponseDto {
    private String nome;
    private Map<String,Integer> jutsus;
    private int chakra;
    private int vida;
}
