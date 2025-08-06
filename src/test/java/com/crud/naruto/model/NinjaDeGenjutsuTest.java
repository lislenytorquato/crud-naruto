package com.crud.naruto.model;


import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NinjaDeGenjutsuTest {

    private static NinjaDeGenjutsu ninjaDeGenjutsu;
    private static Personagem sakura;

    @BeforeAll
    static void setup(){
        JUTSUS_PERSONAGEM_SAKURA.add(GENJUTSU);
        ninjaDeGenjutsu  = new NinjaDeGenjutsu(ID_PERSONAGEM_SAKURA,NOME_PERSONAGEM_SAKURA,IDADE_PERSONAGEM_SAKURA,ALDEIA_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA);
        sakura = criarPersonagemSakura();
    }

    @DisplayName("1- deve testar construtor")
    @Test
    @Order(1)
    void deveTestarConstrutor(){

        AssertionsHelper.assertEqualsParaTestarConstrutor(ninjaDeGenjutsu,sakura);
    }

    @DisplayName("2- deve testar setter")
    @Test
    @Order(3)
    void deveTestarSetter(){

        Personagem naruto = criarPersonagemNaruto();
        setPersonagem(ninjaDeGenjutsu,naruto);
        AssertionsHelper.assertEqualsParaSetter(ninjaDeGenjutsu,naruto);
    }

    @DisplayName("3- deve testar toString")
    @Test
    @Order(2)
    void deveTestarToString(){

        AssertionsHelper.assertTrueParaToString(ninjaDeGenjutsu,sakura);
    }

    @DisplayName("4- Deve testar adicionar jutsu")
    @Test
    @Order(4)
    void deveTestarAdicionarJutsu(){
        boolean jutsuAdicionado = ninjaDeGenjutsu.adicionarJutsu(JUTSU_NOVO);

        AssertionsHelper.assertTrueParaAdicionarJutsu(jutsuAdicionado,ninjaDeGenjutsu);
    }

    @DisplayName("5- Deve testar aumentar chacra")
    @Test
    @Order(5)
    void deveTestarAumentarChakra(){
        int chakraAumentado = ninjaDeGenjutsu.aumentarChakra(QUANTIDADE);

        AssertionsHelper.assertEqualsParaAumentarChakra(ninjaDeGenjutsu,chakraAumentado);
    }

    @DisplayName("6- deve testar usar jutsu")
    @Test
    @Order(6)
    void deveTestarUsarJutsu(){
        String jutsuUsado = ninjaDeGenjutsu.usarJutsu();
        Assertions.assertEquals(USAR_JUTSU_FRASE_GENJUTSU,jutsuUsado);
    }

    @DisplayName("7- deve testar desviar")
    @Test
    @Order(7)
    void deveTestarDesviar(){
        String desvio = ninjaDeGenjutsu.desviar();
        Assertions.assertEquals(DESVIAR_FRASE_GENJUTSU,desvio);
    }





    }
