package com.crud.naruto.helper;

import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Jutsu;
import com.crud.naruto.model.Personagem;
import org.junit.jupiter.api.Assertions;

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

    public static void assertParaAdicionarJutsu(Personagem personagem, String nomeJutsu, Jutsu jutsu){
        Assertions.assertTrue(personagem.getJutsus().get(personagem.getJutsus().size()-1).getNome().contains(nomeJutsu));
        Assertions.assertEquals(jutsu.getDano(), personagem.getJutsus().get(personagem.getJutsus().size()-1).getDano());
        Assertions.assertEquals(jutsu.getConsumoDeChakra(), personagem.getJutsus().get(personagem.getJutsus().size()-1).getConsumoDeChakra());
    }

    public static void assertEqualsParaAumentarChakra(Personagem personagem, int chakraAumentado){
        Assertions.assertEquals(personagem.getChakra(),chakraAumentado);
    }

    public  static void assertEqualsParaCompararComResponse(PersonagemResponseDto response, PersonagemResponseDto responseDto) {

            Assertions.assertEquals(response.getNome(), responseDto.getNome());
            Assertions.assertEquals(response.getJutsus().get(0).getDano(), responseDto.getJutsus().get(0).getDano());
            Assertions.assertEquals(response.getJutsus().get(0).getConsumoDeChakra(), responseDto.getJutsus().get(0).getConsumoDeChakra());
            Assertions.assertEquals(response.getChakra(), responseDto.getChakra());

    }
}
