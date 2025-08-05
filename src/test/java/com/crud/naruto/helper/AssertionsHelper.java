package com.crud.naruto.helper;

import com.crud.naruto.model.Personagem;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static com.crud.naruto.helper.TestHelper.JUTSU_NOVO;

public class AssertionsHelper {

    public static void assertEqualsParaTestarConstrutor(Personagem personagem, String nome, int idade, String aldeia, List<String> jutsus, int chackra){
        Assertions.assertEquals(nome,personagem.getNome());
        Assertions.assertEquals(idade,personagem.getIdade());
        Assertions.assertEquals(aldeia,personagem.getAldeia());
        Assertions.assertEquals(jutsus,personagem.getJutsus());
        Assertions.assertEquals(chackra,personagem.getChakra());
    }

    public static void assertEqualsParaSetter(Personagem personagem, Personagem personagemNovo){
        Assertions.assertEquals(personagemNovo.getNome(),personagem.getNome());
        Assertions.assertEquals(personagemNovo.getIdade(),personagem.getIdade());
        Assertions.assertEquals(personagemNovo.getAldeia(),personagem.getAldeia());
        Assertions.assertEquals(personagemNovo.getJutsus(),personagem.getJutsus());
        Assertions.assertEquals(personagemNovo.getChakra(),personagem.getChakra());
    }

    public static void assertTrueParaToString(Personagem personagem, String nome, int idade, String aldeia, List<String> jutsus, int chackra){
        Assertions.assertTrue(personagem.toString().contains(nome));
        Assertions.assertTrue(personagem.toString().contains(Integer.toString(idade)));
        Assertions.assertTrue(personagem.toString().contains(aldeia));
        Assertions.assertTrue(personagem.toString().contains(jutsus.toString()));
        Assertions.assertTrue(personagem.toString().contains(Integer.toString(chackra)));
    }

    public static void assertTrueParaAdicionarJutsu(boolean jutsuAdicionado, Personagem personagem){
        Assertions.assertTrue(jutsuAdicionado);
        Assertions.assertTrue(personagem.getJutsus().contains(JUTSU_NOVO));
    }

    public static void assertEqualsParaAumentarChakra(Personagem personagem, int chakraAumentado){
        Assertions.assertEquals(personagem.getChakra(),chakraAumentado);
    }

}
