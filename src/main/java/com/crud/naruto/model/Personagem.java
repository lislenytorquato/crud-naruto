package com.crud.naruto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Personagem {

    private String nome;
    private int idade;
    private String aldeia;
    private List<String> jutsus;
    private int chakra;

    public List<String> adicionarJutsu(String novoJutsu){
         jutsus.add(novoJutsu);
         return jutsus;
    }
    public int aumentarChakra(int quantidade){
        return chakra += quantidade;
    }


}
