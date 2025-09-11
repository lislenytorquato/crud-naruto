package com.crud.naruto.model;


import com.crud.naruto.interfaces.Ninja;

import java.util.List;
import java.util.Map;

public class NinjaDeNinjutsu extends Personagem implements Ninja {


    public NinjaDeNinjutsu(Long id, String nome, List<Jutsu> jutsus, int chakra, int vida) {
        super(id, nome, jutsus, chakra,vida);
    }

    @Override
    public String usarJutsu() {
        this.getJutsus().forEach(jutsu -> {
            jutsu.setDano(25);
            jutsu.setConsumoDeChakra(10);
        });
        return "Ataque de Ninjutsu!!";
    }

    @Override
    public String desviar(Personagem personagem, boolean conseguiuDesviar) {
        if (conseguiuDesviar){
            return "Desviei com Ninjutsu!!";
        }
        else {
            this.getJutsus().forEach(jutsu -> {
                personagem.setVida(personagem.getVida()- jutsu.getDano());
            });
            return "Não desviei!! Vida: "+personagem.getVida();
        }
    }
}
