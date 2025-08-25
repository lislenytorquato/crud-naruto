package com.crud.naruto.model;


import com.crud.naruto.dto.JutsuDto;
import com.crud.naruto.interfaces.Ninja;

import java.util.Map;

public class NinjaDeNinjutsu extends Personagem implements Ninja {


    public NinjaDeNinjutsu(Long id, String nome, Map<String,Jutsu> jutsus, int chakra, int vida) {
        super(id, nome, jutsus, chakra,vida);
    }

    @Override
    public String usarJutsu() {
        this.getJutsus().get("Ninjutsu").setDano(25);
        this.getJutsus().get("Ninjutsu").setConsumoDeChakra(10);
        return "Ataque de Ninjutsu!!";
    }

    @Override
    public String desviar(Personagem personagem, boolean conseguiuDesviar) {
        if (conseguiuDesviar){
            return "Desviei com Ninjutsu!!";
        }
        else {
            personagem.setVida(personagem.getVida()-this.getJutsus().get("Ninjutsu").getDano());
            return "Não desviei!! Vida: "+personagem.getVida();
        }
    }
}
