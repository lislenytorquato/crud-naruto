package com.crud.naruto.model;

import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NinjaDeTaijutsuTest {


    private static NinjaDeTaijutsu ninjaDeTaijutsu;
    private static Personagem rockieLee;

    @BeforeAll
    static void setup(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE.add(TAIJUTSU);
        ninjaDeTaijutsu = new NinjaDeTaijutsu(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE, IDADE_PERSONAGEM_ROCKIE_LEE, ALDEIA_PERSONAGEM_ROCKIE_LEE, JUTSUS_PERSONAGEM_ROCKIE_LEE, CHAKRA_PERSONAGEM_ROCKIE_LEE);
        rockieLee = criarPersonagemRockieLee();
    }

    @DisplayName("1- deve testar construtor")
    @Test
    @Order(1)
    void deveTestarConstrutor(){

        AssertionsHelper.assertEqualsParaTestarConstrutor(ninjaDeTaijutsu, rockieLee);
    }

    @DisplayName("2- deve testar setter")
    @Test
    @Order(3)
    void deveTestarSetter(){

        Personagem sakura = criarPersonagemSakura();
        setPersonagem(ninjaDeTaijutsu,sakura);
        AssertionsHelper.assertEqualsParaSetter(ninjaDeTaijutsu,sakura);
    }

    @DisplayName("3- deve testar toString")
    @Test
    @Order(2)
    void deveTestarToString(){

        AssertionsHelper.assertTrueParaToString(ninjaDeTaijutsu, rockieLee);
    }

    @DisplayName("4- Deve testar adicionar jutsu")
    @Test
    @Order(4)
    void deveTestarAdicionarJutsu(){
        boolean jutsuAdicionado = ninjaDeTaijutsu.adicionarJutsu(JUTSU_NOVO);

        AssertionsHelper.assertTrueParaAdicionarJutsu(jutsuAdicionado, ninjaDeTaijutsu);
    }

    @DisplayName("5- Deve testar aumentar chacra")
    @Test
    @Order(5)
    void deveTestarAumentarChakra(){
        int chakraAumentado = ninjaDeTaijutsu.aumentarChakra(QUANTIDADE);

        AssertionsHelper.assertEqualsParaAumentarChakra(ninjaDeTaijutsu,chakraAumentado);
    }

    @DisplayName("6- deve testar usar jutsu")
    @Test
    @Order(6)
    void deveTestarUsarJutsu(){
        String jutsuUsado = ninjaDeTaijutsu.usarJutsu();
        Assertions.assertEquals(USAR_JUTSU_FRASE_TAIJUTSU,jutsuUsado);
    }

    @DisplayName("7- deve testar desviar")
    @Test
    @Order(7)
    void deveTestarDesviar(){
        String desvio = ninjaDeTaijutsu.desviar();
        Assertions.assertEquals(DESVIAR_FRASE_TAIJUTSU,desvio);
    }
}
