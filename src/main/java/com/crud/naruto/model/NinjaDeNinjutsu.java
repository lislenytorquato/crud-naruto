package com.crud.naruto.model;


import com.crud.naruto.interfaces.Ninja;

import java.util.List;

public class NinjaDeNinjutsu extends Personagem implements Ninja {


    public NinjaDeNinjutsu(Long id, String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
        super(id, nome, idade, aldeia, jutsus, chakra);
    }

    @Override
    public String usarJutsu() {
        return "Ataque de Ninjutsu!!";
    }

    @Override
    public String desviar() {
        return "Desviei com Ninjutsu!";
    }
}
