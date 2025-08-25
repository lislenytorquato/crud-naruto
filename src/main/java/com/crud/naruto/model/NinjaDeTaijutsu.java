package com.crud.naruto.model;

import com.crud.naruto.interfaces.Ninja;

import java.util.List;
import java.util.Map;

public class NinjaDeTaijutsu extends Personagem implements Ninja {


    public NinjaDeTaijutsu(Long id, String nome, Map<String,Jutsu> jutsus, int chakra, int vida) {
        super(id, nome, jutsus, chakra, vida);
    }

    @Override
    public String usarJutsu() {
        this.getJutsus().get("Taijutsu").setDano(25);
        this.getJutsus().get("Taijutsu").setConsumoDeChakra(10);
        return "Ataque de Taijutsu!!";
    }

    @Override
    public String desviar(Personagem personagem, boolean conseguiuDesviar) {

        if (conseguiuDesviar){
            return "Desviei com Taijutsu!!";
        }
        else {
            personagem.setVida(personagem.getVida()-this.getJutsus().get("Taijutsu").getDano());
            return "Não desviei!! Vida: "+personagem.getVida();
        }

    }
}
