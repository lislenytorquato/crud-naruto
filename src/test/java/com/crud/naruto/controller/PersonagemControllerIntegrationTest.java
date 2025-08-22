package com.crud.naruto.controller;

import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonagemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonagemRepository personagemRepository;

    private static  String jsonPersonagemRockieLee;
    private static  String jsonPersonagemNaruto;
    private static  String jsonPersonagemSakura;

    @BeforeEach
    void setUp() {
        personagemRepository.deleteAll();

        jsonPersonagemRockieLee = "{\"nome\":\"Rockie Lee\",\"idade\":17,\"aldeia\":\"China\",\"jutsus\":[],\"chakra\":125}";

         jsonPersonagemNaruto= "{\"nome\": \"Naruto\", \"idade\": 15, \"aldeia\": \"Japão\", \"jutsus\": [], \"chakra\": 100}";

        jsonPersonagemSakura = "{\"nome\":\"Sakura\",\"idade\":16,\"aldeia\":\"França\",\"jutsus\":[],\"chakra\":120}";
    }

    @DisplayName("1- deve criar personagem")
    @Test
    void deveCriar() throws Exception {

        mockMvc.perform(post("/api/personagem")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPersonagemRockieLee)).andExpect(status().isCreated());
    }

    @DisplayName("2- deve editar personagem")
    @Test
    void deveEditar() throws Exception {
        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(put("/api/personagem/"+personagemSalvo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPersonagemSakura)).andExpect(status().isOk());
    }

    @DisplayName("3- deve deletar personagem")
    @Test
    void deveDeletar() throws Exception {
        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(delete("/api/personagem/"+personagemSalvo.getId()))
                .andExpect(status().isNoContent());
    }

    @DisplayName("4- deve listar personagens")
    @Test
    void develistar() throws Exception {
        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(get("/api/personagem"))
                .andExpect(status().isOk());
    }

    @DisplayName("5- deve adicionar Jutsu")
    @Test
    void deveAdicionarJutsu() throws Exception {
        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(put("/api/personagem/"+personagemSalvo.getId()+"/adiciona-jutsu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\" Taijutsu\""))
                .andExpect(status().isOk());
    }

    @DisplayName("6- deve aumentar chakra")
    @Test
    void deveAumentarChakra() throws Exception {
        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(put("/api/personagem/"+personagemSalvo.getId()+"/aumenta-chakra")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("50"))
                .andExpect(status().isOk());
    }

    @DisplayName("7- deve usar jutsu")
    @Test
    void deveUsarJutsu() throws Exception {

        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/usa-jutsu"))
                .andExpect(status().isOk());
    }
    @DisplayName("7- deve desviar")
    @Test
    void deveDesviar() throws Exception {

        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());

        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/desvia"))
                .andExpect(status().isOk());
    }




}
