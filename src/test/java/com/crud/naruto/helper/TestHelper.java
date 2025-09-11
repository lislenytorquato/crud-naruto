package com.crud.naruto.helper;

import com.crud.naruto.dto.JutsuDto;
import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Jutsu;
import com.crud.naruto.model.NinjaDeTaijutsu;
import com.crud.naruto.model.Personagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHelper {

    public static final String NOME_NINJUTSU = "Ninjutsu";
    public static final String NOME_TAIJUTSU = "Taijutsu";

    public static final Long ID_NINJUTSU = 1L;
    public static final Long ID_TAIJUTSU = 2L;
    public static final int DANO_NINJUTSU = 25;
    public static final int DANO_TAIJUTSU = 25;

    public static final int CONSUMO_CHAKRA_NINJUTSU = 10;
    public static final int CONSUMO_CHAKRA_TAIJUTSU = 10;

    public static JutsuDto ninjutsuDto = new JutsuDto(DANO_NINJUTSU,CONSUMO_CHAKRA_NINJUTSU,NOME_NINJUTSU);
    public static JutsuDto taijutsuDto = new JutsuDto(DANO_TAIJUTSU,CONSUMO_CHAKRA_TAIJUTSU,NOME_TAIJUTSU);
    public static Jutsu ninjutsu = new Jutsu(ID_NINJUTSU,DANO_NINJUTSU,CONSUMO_CHAKRA_NINJUTSU,NOME_NINJUTSU);
    public static Jutsu ninjutsuNarutoSemId = new Jutsu(null,DANO_NINJUTSU,CONSUMO_CHAKRA_NINJUTSU,NOME_NINJUTSU);
    public static Jutsu taijutsu = new Jutsu(ID_TAIJUTSU, DANO_TAIJUTSU,CONSUMO_CHAKRA_TAIJUTSU,NOME_TAIJUTSU);

    public static final Long ID_PERSONAGEM_NARUTO = 1L;
    public static final String NOME_PERSONAGEM_NARUTO = "Naruto";
    public static final Map<String, Jutsu> JUTSUS_PERSONAGEM_NARUTO = new HashMap<>();
    public static final int CHAKRA_PERSONAGEM_NARUTO = 100;
    public static final int VIDA_PERSONAGEM_NARUTO = 100;

    public static final Long ID_PERSONAGEM_SAKURA = 2L;
    public static final String NOME_PERSONAGEM_SAKURA = "Sakura";
    public static final Map<String,Jutsu> JUTSUS_PERSONAGEM_SAKURA = new HashMap<>();
    public static final int CHAKRA_PERSONAGEM_SAKURA = 100;
    public static final int VIDA_PERSONAGEM_SAKURA = 100;


    public static final int QUANTIDADE = 50;

    public static final Long ID_PERSONAGEM_ROCKIE_LEE = 3L;
    public static final String NOME_PERSONAGEM_ROCKIE_LEE = "Rockie Lee";
    public static final List<Jutsu> JUTSUS_PERSONAGEM_ROCKIE_LEE = new ArrayList<>();
    public static final Map<String,JutsuDto> JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO = new HashMap<>();
    public static final Map<String,Jutsu> JUTSUS_PERSONAGEM_ROCKIE_LEE_SEM_ID = new HashMap<>();
    public static final int CHAKRA_PERSONAGEM_ROCKIE_LEE = 100;
    public static final int VIDA_PERSONAGEM_ROCKIE_LEE = 100;

    public static final String CHAKRA_CONSUMIDO_DEFESA_FRASE = " Chakra após defesa: ";
    public static final String CHAKRA_CONSUMIDO_ATAQUE_FRASE = " Chakra após ataque: ";
    public static final String CONTINUE_JOGANDO_FRASE =  "Continue jogando...";
    public static final String PERDEU_FRASE =  "Personagem perdeu!!!";

    public static final String USAR_JUTSU_FRASE_NINJUTSU = "Ataque de Ninjutsu!!";
    public static final String USAR_JUTSU_FRASE_TAIJUTSU = "Ataque de Taijutsu!!";

    public static final String DESVIAR_FRASE_NINJUTSU = "Desviei com Ninjutsu!!";
    public static final String NAO_DESVIEI_FRASE =  "Não desviei!! Vida: ";
    public static final String DESVIAR_FRASE_TAIJUTSU = "Desviei com Taijutsu!!";

    public static Personagem criarPersonagemSakura(){
        JUTSUS_PERSONAGEM_SAKURA.put(NOME_NINJUTSU, ninjutsu);
        return new Personagem(ID_PERSONAGEM_SAKURA,NOME_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA, VIDA_PERSONAGEM_SAKURA);
    }

    public static Personagem criarPersonagemNaruto(){
        Map<String,Jutsu> JUTSUS_PERSONAGEM_NARUTO = new HashMap<>();
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_NINJUTSU,ninjutsu);
        return new Personagem(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,CHAKRA_PERSONAGEM_NARUTO, VIDA_PERSONAGEM_NARUTO);
    }

    public static Personagem criarPersonagemNarutoSemId(){
        Map<String,Jutsu> JUTSUS_PERSONAGEM_NARUTO = new HashMap<>();
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_NINJUTSU,ninjutsu);
        return new Personagem(null,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,CHAKRA_PERSONAGEM_NARUTO, VIDA_PERSONAGEM_NARUTO);
    }

    public static Personagem criarPersonagemRockieLee(){
        Map<String,Jutsu> JUTSUS_PERSONAGEM_ROCKIE_LEE = new HashMap<>();
        JUTSUS_PERSONAGEM_ROCKIE_LEE.put(NOME_TAIJUTSU,taijutsu);
        return new Personagem(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static Personagem criarPersonagemRockieLeeSemId(int chakra, int vida){
        JUTSUS_PERSONAGEM_ROCKIE_LEE_SEM_ID.put(NOME_TAIJUTSU,taijutsu);
        return new Personagem(null,NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE_SEM_ID,chakra,vida);
    }

    public static void setPersonagem(Personagem personagem,Personagem personagemNovo){
        personagem.setNome(personagemNovo.getNome());
        personagem.setJutsus(personagemNovo.getJutsus());
        personagem.setChakra(personagemNovo.getChakra());

    }
    public static PersonagemRequestDto criarRockieLeeRequestDto(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO.put(NOME_TAIJUTSU,taijutsuDto);
        return new PersonagemRequestDto(NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemResponseDto criarRockieLeeResponseDto(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO.put(NOME_TAIJUTSU,taijutsuDto);
        return new PersonagemResponseDto(NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO,CHAKRA_PERSONAGEM_ROCKIE_LEE, VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static Personagem criarPersonagemNarutoSemChakra(){
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_NINJUTSU,ninjutsu);
        return new Personagem(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,0,VIDA_PERSONAGEM_NARUTO);
    }
    public static Personagem criarPersonagemNarutoSemIdESemChakra(){
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_NINJUTSU,ninjutsu);
        return new Personagem(null,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,0,VIDA_PERSONAGEM_NARUTO);
    }
    public static Personagem criarPersonagemRockieLeeSemChakra(){
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_TAIJUTSU,taijutsu);
        return new Personagem(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,0,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static Personagem criarPersonagemNarutoSemVidaESemChakra(){
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_NINJUTSU,ninjutsu);
        return new Personagem(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO,JUTSUS_PERSONAGEM_NARUTO,0,0);
    }
    public static Personagem criarPersonagemRockieLeeSemVidaESemChakra(){
        JUTSUS_PERSONAGEM_NARUTO.put(NOME_TAIJUTSU,taijutsu);
        return new Personagem(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,0,0);
    }
    public static PersonagemRequestDto criarPersonagemRequestDtoNomeNulo(){
        return new PersonagemRequestDto(null,JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemRequestDto criarPersonagemRequestDtoNomeEspacoEmBranco(){
        String NOME_ESPACO_BRANCO = "    ";
        Map<String,JutsuDto> JUTSUS_PERSONAGEM_ROCKIE_LEE_REQUEST = new HashMap<>();
        JUTSUS_PERSONAGEM_ROCKIE_LEE_REQUEST.put(NOME_TAIJUTSU,taijutsuDto);
        return new PersonagemRequestDto(NOME_ESPACO_BRANCO,JUTSUS_PERSONAGEM_ROCKIE_LEE_REQUEST,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemRequestDto criarPersonagemRequestDtoNomeVazio(){
        String NOME_VAZIO = "";
        return new PersonagemRequestDto(NOME_VAZIO,JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemRequestDto criarPersonagemRequestDtoJutsusNulo(){
        return new PersonagemRequestDto(NOME_PERSONAGEM_NARUTO,null,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
    public static PersonagemRequestDto criarPersonagemRequestTodoInvalido(){
        return new PersonagemRequestDto(null,null,VIDA_PERSONAGEM_ROCKIE_LEE);
    }
}
