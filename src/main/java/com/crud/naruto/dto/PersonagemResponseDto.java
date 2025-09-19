package com.crud.naruto.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonagemResponseDto {
    private String nome;
    private List<JutsuDto> jutsus;
    private int chakra;
    private int vida;
    private AldeiaDto aldeiaDto;
}
