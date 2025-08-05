package com.crud.naruto.helper;

import com.crud.naruto.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {


    public static final Long ID_PERSONAGEM_NARUTO = 1L;
    public static final String NOME_PERSONAGEM_NARUTO = "Naruto";
    public static final int IDADE_PERSONAGEM_NARUTO = 15;
    public static final String ALDEIA_PERSONAGEM_NARUTO = "Japão";
    public static final List<String> JUTSUS_PERSONAGEM_NARUTO = new ArrayList<>();
    public static final int CHAKRA_PERSONAGEM_NARUTO = 100;

    public static final Long ID_PERSONAGEM_SAKURA = 2L;
    public static final String NOME_PERSONAGEM_SAKURA = "Sakura";
    public static final int IDADE_PERSONAGEM_SAKURA = 16;
    public static final String ALDEIA_PERSONAGEM_SAKURA = "França";
    public static final List<String> JUTSUS_PERSONAGEM_SAKURA = new ArrayList<>();
    public static final int CHAKRA_PERSONAGEM_SAKURA = 120;

    public static final String JUTSU_NOVO = "Ninjutsu";
    public static final int QUANTIDADE = 50;

    public static final String NINJUTSU = "Ninjutsu";
    public static final String GENJUTSU = "Genjutsu";
    public static final String TAIJUTSU = "Taijutsu";

    public static final Long ID_PERSONAGEM_ROCKIE_LEE = 3L;
    public static final String NOME_PERSONAGEM_ROCKIE_LEE = "Rockie Lee";
    public static final int IDADE_PERSONAGEM_ROCKIE_LEE = 17;
    public static final String ALDEIA_PERSONAGEM_ROCKIE_LEE = "China";
    public static final List<String> JUTSUS_PERSONAGEM_ROCKIE_LEE = new ArrayList<>();
    public static final int CHAKRA_PERSONAGEM_ROCKIE_LEE = 125;

    public static final String USAR_JUTSU_FRASE_GENJUTSU = "Ataque de Genjutsu!!";
    public static final String USAR_JUTSU_FRASE_NINJUTSU = "Ataque de Ninjutsu!!";
    public static final String USAR_JUTSU_FRASE_TAIJUTSU = "Ataque de Taijutsu!!";

    public static final String DESVIAR_FRASE_GENJUTSU = "Desviei com Genjutsu!";
    public static final String DESVIAR_FRASE_NINJUTSU = "Desviei com Ninjutsu!";
    public static final String DESVIAR_FRASE_TAIJUTSU = "Desviei com Taijutsu!";

    public static Personagem criarPersonagemSakura(Personagem personagem){
        personagem.setNome(NOME_PERSONAGEM_SAKURA);
        personagem.setIdade(IDADE_PERSONAGEM_SAKURA);
        personagem.setAldeia(ALDEIA_PERSONAGEM_SAKURA);
        personagem.setJutsus(JUTSUS_PERSONAGEM_SAKURA);
        personagem.setChakra(CHAKRA_PERSONAGEM_SAKURA);

        return personagem;
    }

    public static Personagem criarPersonagemNaruto(Personagem personagem){
        personagem.setNome(NOME_PERSONAGEM_NARUTO);
        personagem.setIdade(IDADE_PERSONAGEM_NARUTO);
        personagem.setAldeia(ALDEIA_PERSONAGEM_NARUTO);
        personagem.setJutsus(JUTSUS_PERSONAGEM_NARUTO);
        personagem.setChakra(CHAKRA_PERSONAGEM_NARUTO);

        return personagem;
    }

    public static Personagem criarPersonagemRockieLee(Personagem personagem){
        personagem.setNome(NOME_PERSONAGEM_ROCKIE_LEE);
        personagem.setIdade(IDADE_PERSONAGEM_ROCKIE_LEE);
        personagem.setAldeia(ALDEIA_PERSONAGEM_ROCKIE_LEE);
        personagem.setJutsus(JUTSUS_PERSONAGEM_ROCKIE_LEE);
        personagem.setChakra(CHAKRA_PERSONAGEM_ROCKIE_LEE);

        return personagem;
    }
}
