package com.crud.naruto.model;

import com.crud.naruto.interfaces.Ninja;

import java.util.List;


public class NinjaDeTaijutsu extends Personagem implements Ninja {


    public NinjaDeTaijutsu(String id, String nome, List<Jutsu> jutsus, int chakra, int vida) {
        super(id, nome, jutsus, chakra, vida);
    }

    @Override
    public String usarJutsu() {
        this.getJutsus().forEach(jutsu -> {
            jutsu.setDano(25);
            jutsu.setConsumoDeChakra(10);
        });
        return "Ataque de Taijutsu!!";
    }

    @Override
    public String desviar(Personagem personagem, boolean conseguiuDesviar) {

        if (conseguiuDesviar){
            return "Desviei com Taijutsu!!";
        }
        else {
            personagem.getJutsus().forEach(jutsu -> {
                personagem.setVida(personagem.getVida()- jutsu.getDano());
            });
            return "Não desviei!! Vida: "+personagem.getVida();
        }

    }
}
