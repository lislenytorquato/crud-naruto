package com.crud.naruto.model;

import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonagemTest {

    private static Personagem personagem;
    private static Personagem naruto;

    @BeforeAll
    static void setup(){

        personagem = new Personagem(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO, IDADE_PERSONAGEM_NARUTO, ALDEIA_PERSONAGEM_NARUTO, JUTSUS_PERSONAGEM_NARUTO, CHAKRA_PERSONAGEM_NARUTO);
        naruto = criarPersonagemNaruto();
    }

    @DisplayName("1- Deve testar anotação lombok All Args Constructor")
    @Test
    @Order(1)
    void deveTestarAllArgsConstructor(){
        JUTSUS_PERSONAGEM_NARUTO.add(NINJUTSU);

        AssertionsHelper.assertEqualsParaTestarConstrutor(personagem,naruto);

    }

    @DisplayName("2- Deve testar anotação lombok setter")
    @Test
    @Order(3)
    void deveTestarSetter(){
        Personagem sakura = criarPersonagemSakura();
        setPersonagem(personagem,sakura);
        AssertionsHelper.assertEqualsParaSetter(personagem,sakura);
    }


    @DisplayName("3- Deve testar anotação lombok toString")
    @Test
    @Order(2)
    void deveTestarToString(){
        AssertionsHelper.assertTrueParaToString(personagem,naruto);
    }

    @DisplayName("4- Deve testar adicionar jutsu")
    @Test
    @Order(4)
    void deveTestarAdicionarJutsu(){
        boolean jutsuAdicionado = personagem.adicionarJutsu(JUTSU_NOVO);

        AssertionsHelper.assertTrueParaAdicionarJutsu(jutsuAdicionado,personagem);
    }

    @DisplayName("5- Deve testar aumentar chacra")
    @Test
    @Order(5)
    void deveTestarAumentarChakra(){
        int chakraAumentado = personagem.aumentarChakra(QUANTIDADE);

        AssertionsHelper.assertEqualsParaAumentarChakra(personagem,chakraAumentado);
    }
}
