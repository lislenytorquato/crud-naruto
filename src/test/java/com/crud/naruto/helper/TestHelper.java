package com.crud.naruto.helper;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.NinjaDeTaijutsu;
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

    public static Personagem criarPersonagemSakura(){
        return new Personagem(ID_PERSONAGEM_SAKURA,NOME_PERSONAGEM_SAKURA,IDADE_PERSONAGEM_SAKURA,ALDEIA_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA);
    }

    public static Personagem criarPersonagemNaruto(){

        return new Personagem(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO,IDADE_PERSONAGEM_NARUTO,ALDEIA_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,CHAKRA_PERSONAGEM_NARUTO);
    }

    public static Personagem criarPersonagemNarutoSemId(){
        JUTSUS_PERSONAGEM_NARUTO.add(NINJUTSU);
        return new Personagem(null,NOME_PERSONAGEM_NARUTO,IDADE_PERSONAGEM_NARUTO,ALDEIA_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,CHAKRA_PERSONAGEM_NARUTO);
    }

    public static Personagem criarPersonagemRockieLee(){

        return new Personagem(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,IDADE_PERSONAGEM_ROCKIE_LEE,ALDEIA_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE);
    }

    public static NinjaDeTaijutsu criarPersonagemRockieLeeNinjaDeTaijutsu(){

        return new NinjaDeTaijutsu(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,IDADE_PERSONAGEM_ROCKIE_LEE,ALDEIA_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE);
    }

    public static void setPersonagem(Personagem personagem,Personagem personagemNovo){
        personagem.setNome(personagemNovo.getNome());
        personagem.setIdade(personagemNovo.getIdade());
        personagem.setAldeia(personagemNovo.getAldeia());
        personagem.setJutsus(personagemNovo.getJutsus());
        personagem.setChakra(personagemNovo.getChakra());

    }
    public static PersonagemRequestDto criarRockieLeeRequestDto(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE.add(TAIJUTSU);
        return new PersonagemRequestDto(NOME_PERSONAGEM_ROCKIE_LEE,IDADE_PERSONAGEM_ROCKIE_LEE,ALDEIA_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemResponseDto criarRockieLeeResponseDto(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE.add(TAIJUTSU);
        return new PersonagemResponseDto(NOME_PERSONAGEM_ROCKIE_LEE,IDADE_PERSONAGEM_ROCKIE_LEE,ALDEIA_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE);
    }
}
