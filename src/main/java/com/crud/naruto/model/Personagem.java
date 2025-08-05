package com.crud.naruto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@AllArgsConstructor
public class Personagem {

    @ToString.Exclude
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private int idade;

    @Getter
    @Setter
    private String aldeia;

    @Getter
    @Setter
    private List<String> jutsus;

    @Getter
    @Setter
    private int chakra;

    public boolean adicionarJutsu(String novoJutsu){
        return jutsus.add(novoJutsu);

    }
    public int aumentarChakra(int quantidade){
        return chakra += quantidade;
    }


}
