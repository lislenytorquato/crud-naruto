package com.crud.naruto.model;

import com.crud.naruto.helper.AssertionsHelper;
import com.crud.naruto.helper.TestHelper;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NinjaDeNinjutsuTest {


    private static NinjaDeNinjutsu ninjaDeNinjutsu;
    private static Personagem naruto;

    @BeforeAll
    static void setup(){

        ninjaDeNinjutsu  = new NinjaDeNinjutsu(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO, JUTSUS_PERSONAGEM_NARUTO, CHAKRA_PERSONAGEM_NARUTO, VIDA_PERSONAGEM_NARUTO);
        naruto = criarPersonagemNaruto();

    }

    @DisplayName("1- deve testar construtor")
    @Test
    @Order(1)
    void deveTestarConstrutor(){

        AssertionsHelper.assertEqualsPersonagem(ninjaDeNinjutsu,naruto);
    }

    @DisplayName("2- deve testar setter")
    @Test
    @Order(3)
    void deveTestarSetter(){

        Personagem sakura = criarPersonagemSakura();
        setPersonagem(ninjaDeNinjutsu,sakura);
        AssertionsHelper.assertEqualsPersonagem(ninjaDeNinjutsu,sakura);
    }

    @DisplayName("3- deve testar toString")
    @Test
    @Order(2)
    void deveTestarToString(){

        AssertionsHelper.assertTrueParaToString(ninjaDeNinjutsu,naruto);
    }

    @DisplayName("4- Deve testar adicionar jutsu")
    @Test
    @Order(4)
    void deveTestarAdicionarJutsu(){
        ninjaDeNinjutsu.adicionarJutsu(taijutsu);

        AssertionsHelper.assertParaAdicionarJutsu(ninjaDeNinjutsu, NOME_TAIJUTSU,taijutsu);
    }

    @DisplayName("5- Deve testar aumentar chacra")
    @Test
    @Order(5)
    void deveTestarAumentarChakra(){
        int chakraAumentado = ninjaDeNinjutsu.aumentarChakra(QUANTIDADE);

        AssertionsHelper.assertEqualsParaAumentarChakra(ninjaDeNinjutsu,chakraAumentado);
    }

    @DisplayName("6- deve testar usar jutsu")
    @Test
    @Order(6)
    void deveTestarUsarJutsu(){
        String jutsuUsado = ninjaDeNinjutsu.usarJutsu();
        Assertions.assertEquals(USAR_JUTSU_FRASE_NINJUTSU,jutsuUsado);
    }

    @DisplayName("7- deve testar desviar quando conseguiuDesviar é true")
    @Test
    @Order(7)
    void deveTestarDesviarQuandoConseguiuDesviarEhTrue(){
        String desvio = ninjaDeNinjutsu.desviar(ninjaDeNinjutsu,true);
        Assertions.assertTrue(desvio.contains(DESVIAR_FRASE_NINJUTSU));
    }

    @DisplayName("7- deve testar desviar quando conseguiuDesviar é false")
    @Test
    @Order(7)
    void deveTestarDesviar(){
        String desvio = ninjaDeNinjutsu.desviar(ninjaDeNinjutsu,false);
        Assertions.assertTrue(desvio.contains(NAO_DESVIEI_FRASE));
    }
}
