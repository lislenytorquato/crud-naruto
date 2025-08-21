package com.crud.naruto.model;


import com.crud.naruto.interfaces.Ninja;

import java.util.Map;

public class NinjaDeNinjutsu extends Personagem implements Ninja {


    public NinjaDeNinjutsu(Long id, String nome, Map<String,Jutsu> jutsus, int chakra, int vida) {
        super(id, nome, jutsus, chakra,vida);
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
