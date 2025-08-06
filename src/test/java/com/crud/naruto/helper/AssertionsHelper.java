package com.crud.naruto.helper;

import com.crud.naruto.model.Personagem;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.crud.naruto.helper.TestHelper.JUTSU_NOVO;

public class AssertionsHelper {

    public static void assertEqualsParaTestarConstrutor(Personagem personagem, Personagem personagemAtributos){

        Assertions.assertEquals(personagemAtributos.getNome(),personagem.getNome());
        Assertions.assertEquals(personagemAtributos.getIdade(),personagem.getIdade());
        Assertions.assertEquals(personagemAtributos.getAldeia(),personagem.getAldeia());
        Assertions.assertEquals(personagemAtributos.getJutsus(),personagem.getJutsus());
        Assertions.assertEquals(personagemAtributos.getChakra(),personagem.getChakra());
    }

    public static void assertEqualsParaSetter(Personagem personagem, Personagem personagemAtributos){
        Assertions.assertEquals(personagemAtributos.getNome(),personagem.getNome());
        Assertions.assertEquals(personagemAtributos.getIdade(),personagem.getIdade());
        Assertions.assertEquals(personagemAtributos.getAldeia(),personagem.getAldeia());
        Assertions.assertEquals(personagemAtributos.getJutsus(),personagem.getJutsus());
        Assertions.assertEquals(personagemAtributos.getChakra(),personagem.getChakra());
    }

    public static void assertTrueParaToString(Personagem personagem, Personagem personagemAtributos){
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getNome()));
        Assertions.assertTrue(personagem.toString().contains(Integer.toString(personagemAtributos.getIdade())));
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getAldeia()));
        Assertions.assertTrue(personagem.toString().contains(personagemAtributos.getJutsus().get(0)));
        Assertions.assertTrue(personagem.toString().contains(Integer.toString(personagemAtributos.getChakra())));
    }

    public static void assertTrueParaAdicionarJutsu(boolean jutsuAdicionado, Personagem personagem){
        Assertions.assertTrue(jutsuAdicionado);
        Assertions.assertTrue(personagem.getJutsus().contains(JUTSU_NOVO));
    }

    public static void assertEqualsParaAumentarChakra(Personagem personagem, int chakraAumentado){
        Assertions.assertEquals(personagem.getChakra(),chakraAumentado);
    }

}
