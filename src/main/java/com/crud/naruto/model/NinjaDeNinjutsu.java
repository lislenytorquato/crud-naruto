package com.crud.naruto.model;

import lombok.AllArgsConstructor;

import java.util.List;


public class NinjaDeNinjutsu extends Personagem{

    public NinjaDeNinjutsu(String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
        super(nome, idade, aldeia, jutsus, chakra);
    }


}
