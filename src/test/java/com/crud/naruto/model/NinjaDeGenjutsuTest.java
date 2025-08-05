package com.crud.naruto.model;


import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import static com.crud.naruto.helper.TestHelper.*;

public class NinjaDeGenjutsuTest {

    private static NinjaDeGenjutsu ninjaDeGenjutsu;


    @BeforeAll
    static void setup(){
        JUTSUS_PERSONAGEM_SAKURA.add(GENJUTSU);
        ninjaDeGenjutsu  = new NinjaDeGenjutsu(ID_PERSONAGEM_SAKURA,NOME_PERSONAGEM_SAKURA,IDADE_PERSONAGEM_SAKURA,ALDEIA_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA);
    }
    @DisplayName("1- deve testar construtor")
    @Test
    void deveTestarConstrutor(){

        AssertionsHelper.assertEqualsParaTestarConstrutor(ninjaDeGenjutsu,NOME_PERSONAGEM_SAKURA,IDADE_PERSONAGEM_SAKURA,ALDEIA_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA);
    }

    @DisplayName("2- deve testar setter")
    @Test
    void deveTestarSetter(){

        Personagem naruto = criarPersonagemNaruto(ninjaDeGenjutsu);
        AssertionsHelper.assertEqualsParaSetter(ninjaDeGenjutsu,naruto);
    }

    @DisplayName("3- deve testar toString")
    @Test
    void deveTestarToString(){

        AssertionsHelper.assertTrueParaToString(ninjaDeGenjutsu,NOME_PERSONAGEM_SAKURA,IDADE_PERSONAGEM_SAKURA,ALDEIA_PERSONAGEM_SAKURA,JUTSUS_PERSONAGEM_SAKURA,CHAKRA_PERSONAGEM_SAKURA);
    }

    @DisplayName("4- deve testar toString")
    @Test
    void deveTestarUsarJutsu(){
        String jutsuUsado = ninjaDeGenjutsu.usarJutsu();
    }

    @DisplayName("4- Deve testar adicionar jutsu")
    @Test
    void deveTestarAdicionarJutsu(){
        boolean jutsuAdicionado = ninjaDeGenjutsu.adicionarJutsu(JUTSU_NOVO);

        AssertionsHelper.assertTrueParaAdicionarJutsu(jutsuAdicionado,ninjaDeGenjutsu);
    }

    @DisplayName("5- Deve testar aumentar chacra")
    @Test
    void deveTestarAumentarChakra(){
        int chakraAumentado = ninjaDeGenjutsu.aumentarChakra(QUANTIDADE);

        AssertionsHelper.assertEqualsParaAumentarChakra(ninjaDeGenjutsu,chakraAumentado);
    }



    }
