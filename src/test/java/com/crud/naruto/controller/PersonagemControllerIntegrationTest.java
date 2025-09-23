package com.crud.naruto.controller;

import com.crud.naruto.dto.AldeiaDto;
import com.crud.naruto.dto.JutsuDto;
import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.Jutsu;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.JutsuRepository;
import com.crud.naruto.repository.PersonagemRepository;
import com.crud.naruto.service.PersonagemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static com.crud.naruto.helper.TestHelper.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WireMockTest(httpPort = 8089)
public class PersonagemControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    PersonagemService personagemService;



    private PersonagemRequestDto personagemRockieLeeRequestDto;
    private PersonagemResponseDto personagemRockieLeeResponseDto;
    private PersonagemRequestDto personagemNarutoRequestDto;
    private PersonagemResponseDto personagemNarutoResponseDto;
    private AldeiaDto aldeiaDto;

    @BeforeEach
    void setUp() {
        aldeiaDto = new AldeiaDto(NOME_ALDEIA,LOCALIZACAO_ALDEIA);
        JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO.add(taijutsuDto);
        JUTSUS_DTO_PERSONAGEM_NARUTO.add(ninjutsuDto);

        personagemRockieLeeRequestDto = PersonagemRequestDto.builder()
                .nome(NOME_PERSONAGEM_ROCKIE_LEE)
                .jutsus(JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO)
                .vida(100)
                .build();
        personagemRockieLeeResponseDto = new PersonagemResponseDto();
        personagemRockieLeeResponseDto.setNome(NOME_PERSONAGEM_ROCKIE_LEE);
        personagemRockieLeeResponseDto.setJutsus(JUTSUS_PERSONAGEM_ROCKIE_LEE_DTO);
        personagemRockieLeeResponseDto.setChakra(CHAKRA_PERSONAGEM_ROCKIE_LEE);
        personagemRockieLeeResponseDto.setVida(VIDA_PERSONAGEM_ROCKIE_LEE);

        personagemNarutoRequestDto = PersonagemRequestDto.builder()
                .nome(NOME_PERSONAGEM_NARUTO)
                .jutsus(JUTSUS_DTO_PERSONAGEM_NARUTO)
                .vida(100)
                .build();
        personagemNarutoResponseDto = PersonagemResponseDto.builder()
                .nome(NOME_PERSONAGEM_NARUTO)
                .jutsus(JUTSUS_DTO_PERSONAGEM_NARUTO)
                .aldeiaDto(aldeiaDto)
                .vida(100)
                .build();

    }

    @DisplayName("1- deve criar personagem")
    @Test
    void deveCriar() throws Exception {

        WireMock.stubFor(WireMock.get("/aldeia/" + personagemRockieLeeRequestDto.getNome())
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(aldeiaDto))));

        Mockito.when(personagemService.criarPersonagem(personagemRockieLeeRequestDto)).thenReturn(personagemRockieLeeResponseDto);

        mockMvc.perform(post("/api/personagem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personagemRockieLeeRequestDto)))
                .andExpect(status().isCreated());
    }

    @DisplayName("2- deve editar personagem")
    @Test
    void deveEditar() throws Exception {

        Optional<Personagem> optionalPersonagem = Optional.of(TestHelper.criarPersonagemRockieLee());

        WireMock.stubFor(WireMock.get("/aldeia/" + personagemRockieLeeRequestDto.getNome())
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(aldeiaDto))));

        mockMvc.perform(put("/api/personagem/"+optionalPersonagem.get().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personagemNarutoRequestDto))).andExpect(status().isOk());
    }

    @DisplayName("3- deve deletar personagem")
    @Test
    void deveDeletar() throws Exception {

        mockMvc.perform(delete("/api/personagem/"+ID_PERSONAGEM_ROCKIE_LEE))
                .andExpect(status().isNoContent());
    }

    @DisplayName("4- deve listar personagens")
    @Test
    void develistar() throws Exception {
        WireMock.stubFor(WireMock.get("/aldeia/" + personagemRockieLeeRequestDto.getNome())
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(aldeiaDto))));

        mockMvc.perform(get("/api/personagem"))
                .andExpect(status().isOk());
    }
//
//    @DisplayName("5- deve adicionar Jutsu")
//    @Test
//    void deveAdicionarJutsu() throws Exception {
//
//
//        JutsuDto jutsuDto = new JutsuDto(25,10,"Ninjutsu");
//
//
//        Jutsu jutsuSalvo = jutsuRepository.save(TestHelper.ninjutsuNarutoSemId);
//        JUTSUS_PERSONAGEM_NARUTO.add(jutsuSalvo);
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());
//
//        mockMvc.perform(put("/api/personagem/"+personagemSalvo.getId()+"/adiciona-jutsu")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(jutsuDto)))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("6- deve aumentar chakra")
//    @Test
//    void deveAumentarChakra() throws Exception {
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());
//
//        mockMvc.perform(put("/api/personagem/"+personagemSalvo.getId()+"/aumenta-chakra")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("50"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("7- deve atacar ninjutsu quando chakra eh maior que zero")
//    @Test
//    void deveAtacarNinjutsuQuandoChakraEhMaiorQueZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/ataque"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("8- deve atacar taijutsu quando chakra eh maior que zero")
//    @Test
//    void deveAtacarTaijutsuQuandoChakraEhMaiorQueZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemRockieLeeSemId(100,100));
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/ataque"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("9- deve retornar 200 quando chakra ninjutsu eh igual zero")
//    @Test
//    void deveRetornar200QuandoChakraNinjutsuEhIgualAZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemIdESemChakra());
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/ataque"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("9- deve retornar 200 quando chakra taijutsu eh igual zero")
//    @Test
//    void deveRetornar200QuandoChakraTaijutsuEhIgualAZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemRockieLeeSemId(0,100));
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/ataque"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("10- deve defender ninjutsu quando chakra eh maior que zero")
//    @Test
//    void deveDefenderNinjutsuQuandoChakraEhMaiorQueZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemId());
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/defesa"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(DESVIAR_FRASE_NINJUTSU+CHAKRA_CONSUMIDO_DEFESA_FRASE+(personagemSalvo.getChakra()-CONSUMO_CHAKRA_NINJUTSU)));
//    }
//
//    @DisplayName("11- deve defender taijutsu quando chakra eh maior que zero")
//    @Test
//    void deveDefenderTaijutsuQuandoChakraEhMaiorQueZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemRockieLeeSemId(100,100));
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/defesa"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("12- deve retornar 200 quando defender com ninjutsu e chakra  eh igual a zero")
//    @Test
//    void deveRetornar200QuandoDefenderNinjutsuChakraEhIgualAZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemNarutoSemIdESemChakra());
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/defesa"))
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("13- deve retornar 200 quando derrotado com vida igual a zero")
//    @Test
//    void deveRetornar200QuandoDerrotadoComVidaIgualAZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemRockieLeeSemId(100,0));
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/derrota"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(PERDEU_FRASE));
//    }
//
//    @DisplayName("14- deve retornar 200 quando derrotado com chakra igual a zero")
//    @Test
//    void deveRetornar200QuandoDerrotadoComChakraIgualAZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemRockieLeeSemId(0,100));
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/derrota"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(PERDEU_FRASE));;
//    }
//
//    @DisplayName("14- deve retornar 200 quando derrota tem chakra e vida diferentes de zero")
//    @Test
//    void deveRetornar200QuandoDerrotaTemChakraEVidaDiferenteDeZero() throws Exception {
//
//        Personagem personagemSalvo = personagemRepository.save(TestHelper.criarPersonagemRockieLeeSemId(100,100));
//
//        mockMvc.perform(get("/api/personagem/"+personagemSalvo.getId()+"/derrota"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(CONTINUE_JOGANDO_FRASE));
//    }
//
//

}
