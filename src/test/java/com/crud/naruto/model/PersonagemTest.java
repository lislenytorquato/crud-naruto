package com.crud.naruto.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


public class PersonagemTest {

    private static Personagem personagem;

    @BeforeAll
    static void setup(){
        personagem = new Personagem("Naruto",15,"Japão", List.of("Taijutsu"),100);
    }

    @DisplayName("Deve testar anotação lombok All Args Constructor")
    @Test
    void deveTestarAllArgsConstructor(){
        Assertions.assertEquals("Naruto",personagem.getNome());
        Assertions.assertEquals(15,personagem.getIdade());
        Assertions.assertEquals("Japão",personagem.getAldeia());
        Assertions.assertEquals(List.of("Taijutsu"),personagem.getJutsus());
        Assertions.assertEquals(100,personagem.getChakra());
    }
}
