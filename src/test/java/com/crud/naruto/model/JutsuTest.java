package com.crud.naruto.model;

import com.crud.naruto.helper.AssertionsHelper;
import org.junit.jupiter.api.*;

import static com.crud.naruto.helper.TestHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JutsuTest {

    private static Jutsu jutsu;

    @BeforeAll
    static void setup(){
        jutsu = new Jutsu(ID_NINJUTSU,DANO_NINJUTSU,CONSUMO_CHAKRA_NINJUTSU,NOME_NINJUTSU);
    }

    @DisplayName("1- Deve testar anotação lombok All Args Constructor")
    @Test
    @Order(1)
    void deveTestarAllArgsConstructor(){

        Assertions.assertEquals(ID_NINJUTSU,jutsu.getId());
        Assertions.assertEquals(DANO_NINJUTSU,jutsu.getDano());
        Assertions.assertEquals(CONSUMO_CHAKRA_NINJUTSU,jutsu.getConsumoDeChakra());
        Assertions.assertEquals(NOME_NINJUTSU,jutsu.getNome());

    }

    @DisplayName("2- Deve testar anotação lombok setter")
    @Test
    @Order(3)
    void deveTestarSetter(){
        jutsu = new Jutsu();
        jutsu.setDano(DANO_TAIJUTSU);
        jutsu.setConsumoDeChakra(CONSUMO_CHAKRA_TAIJUTSU);
        jutsu.setNome(NOME_TAIJUTSU);

        Assertions.assertEquals(DANO_TAIJUTSU,jutsu.getDano());
        Assertions.assertEquals(CONSUMO_CHAKRA_NINJUTSU,jutsu.getConsumoDeChakra());
        Assertions.assertEquals(NOME_TAIJUTSU,jutsu.getNome());

    }


    @DisplayName("3- Deve testar anotação lombok toString")
    @Test
    @Order(2)
    void deveTestarToString(){

        Assertions.assertTrue(jutsu.toString().contains(Integer.toString(DANO_NINJUTSU)));
        Assertions.assertTrue(jutsu.toString().contains(Integer.toString(CONSUMO_CHAKRA_NINJUTSU)));
        Assertions.assertTrue(jutsu.toString().contains(NOME_NINJUTSU));
    }

}
