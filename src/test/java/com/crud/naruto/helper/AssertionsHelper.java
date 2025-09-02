package com.crud.naruto.helper;

import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Jutsu;
import com.crud.naruto.model.Personagem;
import org.junit.jupiter.api.Assertions;

public class AssertionsHelper {

    public static void assertEqualsPersonagem(Personagem personagem, Personagem personagemAtributos){

        Assertions.assertEquals(personagemAtributos.getNome(),personagem.getNome());
        Assertions.assertEquals(personagemAtributos.getJutsu(),personagem.getJutsu());
        Assertions.assertEquals(personagemAtributos.getChakra(),personagem.getChakra());
    }

    public static void assertTrueParaToString(Personagem personagem, Personagem personagemAtributos){
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getNome()));
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getJutsu().toString()));
        Assertions.assertTrue(personagem.toString().contains(Integer.toString(personagemAtributos.getChakra())));
    }

    public static void assertParaAdicionarJutsu(Personagem personagem, String nomeJutsu, Jutsu jutsu){
        Assertions.assertTrue(personagem.getJutsu().containsKey(nomeJutsu));
        Assertions.assertEquals(jutsu.getDano(),personagem.getJutsu().get(nomeJutsu).getDano());
        Assertions.assertEquals(jutsu.getConsumoDeChakra(),personagem.getJutsu().get(nomeJutsu).getConsumoDeChakra());
    }

    public static void assertEqualsParaAumentarChakra(Personagem personagem, int chakraAumentado){
        Assertions.assertEquals(personagem.getChakra(),chakraAumentado);
    }

    public  static void assertEqualsParaCompararComResponse(PersonagemResponseDto response, PersonagemResponseDto responseDto,Object key){
        Assertions.assertEquals(response.getNome(),responseDto.getNome());
        Assertions.assertEquals(response.getJutsu().get(key).getDano(),responseDto.getJutsu().get(key).getDano());
        Assertions.assertEquals(response.getJutsu().get(key).getConsumoDeChakra(),responseDto.getJutsu().get(key).getConsumoDeChakra());
        Assertions.assertEquals(response.getChakra(),responseDto.getChakra());
    }
}
