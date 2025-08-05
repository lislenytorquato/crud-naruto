package com.crud.naruto.model;


import com.crud.naruto.interfaces.Ninja;

import java.util.List;

public class NinjaDeGenjutsu extends Personagem implements Ninja {


    public NinjaDeGenjutsu(Long id, String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
        super(id, nome, idade, aldeia, jutsus, chakra);
    }

    @Override
    public String usarJutsu() {
        return "Ataque de Genjutsu!!";
    }

    @Override
    public String desviar() {
        return "Desviei com Genjutsu!";
    }
}
