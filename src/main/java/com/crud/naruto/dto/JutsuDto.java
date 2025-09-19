package com.crud.naruto.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JutsuDto {

    private int dano;
    private int consumoDeChakra;
    private String nome;


}
