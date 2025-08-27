package com.crud.naruto.model;

import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NinjaDeTaijutsuTest {


    private static NinjaDeTaijutsu ninjaDeTaijutsu;
    private static Personagem rockieLee;
    private static Jutsu ninjutsu;

    @BeforeAll
    static void setup(){
        JUTSUS_PERSONAGEM_ROCKIE_LEE.put(NOME_TAIJUTSU,taijutsu);
        ninjaDeTaijutsu = new NinjaDeTaijutsu(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE, JUTSUS_PERSONAGEM_ROCKIE_LEE, CHAKRA_PERSONAGEM_ROCKIE_LEE, VIDA_PERSONAGEM_ROCKIE_LEE);
        rockieLee = criarPersonagemRockieLee();
        ninjutsu = new Jutsu(ID_NINJUTSU,DANO_NINJUTSU,CONSUMO_CHAKRA_NINJUTSU,NOME_NINJUTSU);
    }

    @DisplayName("1- deve testar construtor")
    @Test
    @Order(1)
    void deveTestarConstrutor(){

        AssertionsHelper.assertEqualsPersonagem(ninjaDeTaijutsu, rockieLee);
    }

    @DisplayName("2- deve testar setter")
    @Test
    @Order(3)
    void deveTestarSetter(){

        Personagem sakura = criarPersonagemSakura();
        setPersonagem(ninjaDeTaijutsu,sakura);
        AssertionsHelper.assertEqualsPersonagem(ninjaDeTaijutsu,sakura);
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
       ninjaDeTaijutsu.adicionarJutsu(NOME_NINJUTSU, ninjutsu);

        AssertionsHelper.assertParaAdicionarJutsu(ninjaDeTaijutsu, NOME_NINJUTSU,ninjutsu);
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
    @Order(1)
    void deveTestarUsarJutsu(){
        String jutsuUsado = ninjaDeTaijutsu.usarJutsu();
        Assertions.assertEquals(USAR_JUTSU_FRASE_TAIJUTSU,jutsuUsado);
    }

    @DisplayName("7- deve testar desviar quando conseguiuDesviar é true")
    @Test
    @Order(7)
    void deveTestarDesviarQuandoconseguiuDesviarEhTrue(){
        String desvio = ninjaDeTaijutsu.desviar(criarPersonagemRockieLee(),true);
        Assertions.assertTrue(desvio.contains(DESVIAR_FRASE_TAIJUTSU));
    }

    @DisplayName("7- deve testar desviar quando retornarMensagem é false")
    @Test
    @Order(1)
    void deveTestarDesviarQuandoconseguiuDesviarEhFalse(){
        String desvio = ninjaDeTaijutsu.desviar(criarPersonagemRockieLee(),false);
        Assertions.assertTrue(desvio.contains(NAO_DESVIEI_FRASE));
    }
}
