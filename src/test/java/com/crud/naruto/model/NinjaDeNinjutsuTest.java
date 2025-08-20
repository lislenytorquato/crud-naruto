package com.crud.naruto.model;

import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NinjaDeNinjutsuTest {


    private static NinjaDeNinjutsu ninjaDeNinjutsu;
    private static Personagem naruto;
    private static Jutsu jutsu;

    @BeforeAll
    static void setup(){

        ninjaDeNinjutsu  = new NinjaDeNinjutsu(ID_PERSONAGEM_NARUTO,NOME_PERSONAGEM_NARUTO, JUTSUS_PERSONAGEM_NARUTO, CHAKRA_PERSONAGEM_NARUTO, VIDA_PERSONAGEM_NARUTO);
        naruto = criarPersonagemNaruto();
        jutsu = new Jutsu(DANO_TAIJUTSU,CONSUMO_CHAKRA_TAIJUTSU);

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
        ninjaDeNinjutsu.adicionarJutsu(TAIJUTSU,jutsu);

        AssertionsHelper.assertParaAdicionarJutsu(ninjaDeNinjutsu, TAIJUTSU,DANO_TAIJUTSU);
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

    @DisplayName("7- deve testar desviar")
    @Test
    @Order(7)
    void deveTestarDesviar(){
        String desvio = ninjaDeNinjutsu.desviar();
        Assertions.assertEquals(DESVIAR_FRASE_NINJUTSU,desvio);
    }
}
