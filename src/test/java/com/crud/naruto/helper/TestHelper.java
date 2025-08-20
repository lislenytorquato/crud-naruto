package com.crud.naruto.helper;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.NinjaDeTaijutsu;
import com.crud.naruto.model.Personagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {

    public static final String NINJUTSU = "Ninjutsu";
    public static final String TAIJUTSU = "Taijutsu";

    public static final Integer DANO_NINJUTSU = 50;
    public static final Integer DANO_TAIJUTSU = 88;

    public static final Long ID_PERSONAGEM_NARUTO = 1L;
    public static final String NOME_PERSONAGEM_NARUTO = "Naruto";
    public static final Map<String,Integer> JUTSUS_PERSONAGEM_NARUTO = new HashMap<>();
    public static final int CHAKRA_PERSONAGEM_NARUTO = 100;
    public static final int VIDA_PERSONAGEM_NARUTO = 100;

    public static final Long ID_PERSONAGEM_SAKURA = 2L;
    public static final String NOME_PERSONAGEM_SAKURA = "Sakura";
    public static final Map<String,Integer> JUTSUS_PERSONAGEM_SAKURA = new HashMap<>();
    public static final int CHAKRA_PERSONAGEM_SAKURA = 100;
    public static final int VIDA_PERSONAGEM_SAKURA = 100;


    public static final int QUANTIDADE = 50;

    public static final Long ID_PERSONAGEM_ROCKIE_LEE = 3L;
    public static final String NOME_PERSONAGEM_ROCKIE_LEE = "Rockie Lee";
    public static final Map<String,Integer> JUTSUS_PERSONAGEM_ROCKIE_LEE = new HashMap<>();
    public static final int CHAKRA_PERSONAGEM_ROCKIE_LEE = 100;
    public static final int VIDA_PERSONAGEM_ROCKIE_LEE = 100;

    public static final String USAR_JUTSU_FRASE_NINJUTSU = "Ataque de Ninjutsu!!";
    public static final String USAR_JUTSU_FRASE_TAIJUTSU = "Ataque de Taijutsu!!";

    public static final String DESVIAR_FRASE_NINJUTSU = "Desviei com Ninjutsu!";
    public static final String DESVIAR_FRASE_TAIJUTSU = "Desviei com Taijutsu!";

    public static Personagem criarPersonagemSakura(){
        JUTSUS_PERSONAGEM_SAKURA.put(NINJUTSU, DANO_NINJUTSU);
        return new Personagem(ID_PERSONAGEM_SAKURA,NOME_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA, VIDA_PERSONAGEM_SAKURA);
    }

    public static Personagem criarPersonagemNaruto(){
        JUTSUS_PERSONAGEM_NARUTO.put(NINJUTSU,DANO_NINJUTSU);
        return new Personagem(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,CHAKRA_PERSONAGEM_NARUTO, VIDA_PERSONAGEM_NARUTO);
    }

    public static Personagem criarPersonagemNarutoSemId(){
        JUTSUS_PERSONAGEM_NARUTO.put(NINJUTSU,DANO_NINJUTSU);
        return new Personagem(null,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,CHAKRA_PERSONAGEM_NARUTO, VIDA_PERSONAGEM_NARUTO);
    }

    public static Personagem criarPersonagemRockieLee(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE.put(TAIJUTSU,DANO_TAIJUTSU);
        return new Personagem(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE,VIDA_PERSONAGEM_ROCKIE_LEE);
    }

    public static void setPersonagem(Personagem personagem,Personagem personagemNovo){
        personagem.setNome(personagemNovo.getNome());
        personagem.setJutsus(personagemNovo.getJutsus());
        personagem.setChakra(personagemNovo.getChakra());

    }
    public static PersonagemRequestDto criarRockieLeeRequestDto(){
        return new PersonagemRequestDto(NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemResponseDto criarRockieLeeResponseDto(){
        return new PersonagemResponseDto(NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE, VIDA_PERSONAGEM_ROCKIE_LEE);
    }
}
