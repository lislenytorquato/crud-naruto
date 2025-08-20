package com.crud.naruto.helper;

import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Personagem;
import org.junit.jupiter.api.Assertions;

import static com.crud.naruto.helper.TestHelper.DANO_NINJUTSU;
import static com.crud.naruto.helper.TestHelper.NINJUTSU;

public class AssertionsHelper {

    public static void assertEqualsPersonagem(Personagem personagem, Personagem personagemAtributos){

        Assertions.assertEquals(personagemAtributos.getNome(),personagem.getNome());
        Assertions.assertEquals(personagemAtributos.getJutsus(),personagem.getJutsus());
        Assertions.assertEquals(personagemAtributos.getChakra(),personagem.getChakra());
    }

    public static void assertTrueParaToString(Personagem personagem, Personagem personagemAtributos){
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getNome()));
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getJutsus().toString()));
        Assertions.assertTrue(personagem.toString().contains(Integer.toString(personagemAtributos.getChakra())));
    }

    public static void assertParaAdicionarJutsu(Personagem personagem, String jutsu, Integer dano){
        Assertions.assertTrue(personagem.getJutsus().containsKey(jutsu));
        Assertions.assertEquals(dano,personagem.getJutsus().get(jutsu));
    }

    public static void assertEqualsParaAumentarChakra(Personagem personagem, int chakraAumentado){
        Assertions.assertEquals(personagem.getChakra(),chakraAumentado);
    }

    public  static void assertEqualsParaCompararComResponse(PersonagemResponseDto response, PersonagemResponseDto responseDto){
        Assertions.assertEquals(response.getNome(),responseDto.getNome());
        Assertions.assertEquals(response.getJutsus(),responseDto.getJutsus());
        Assertions.assertEquals(response.getChakra(),responseDto.getChakra());
    }
}
