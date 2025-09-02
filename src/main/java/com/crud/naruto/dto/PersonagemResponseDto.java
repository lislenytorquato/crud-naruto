package com.crud.naruto.dto;

import com.crud.naruto.model.Jutsu;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class PersonagemResponseDto {
    private String nome;
    private Map<String, JutsuDto> jutsu;
    private int chakra;
    private int vida;
}
