package com.crud.naruto.controller;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.service.PersonagemService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.crud.naruto.helper.TestHelper.*;

@ExtendWith(MockitoExtension.class)
public class PersonagemControllerTest {

    @InjectMocks
    PersonagemController personagemController;

    @Mock
    PersonagemService personagemService;

    private static PersonagemRequestDto requestDto;
    private static PersonagemResponseDto responseDto;
    private static List<PersonagemResponseDto> listaResponseDto;

    @BeforeAll
    static void setUp(){
        requestDto = TestHelper.criarRockieLeeRequestDto();
         responseDto= TestHelper.criarRockieLeeResponseDto();
        listaResponseDto= List.of(responseDto);
    }


    @DisplayName("1- deve criar personagem")
    @Test
    void deveCriar(){
        Mockito.when(personagemService.criarPersonagem(requestDto)).thenReturn(responseDto);
        ResponseEntity<PersonagemResponseDto> response = personagemController.criar(requestDto);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @DisplayName("2- deve editar personagem")
    @Test
    void deveEditar(){
        Mockito.when(personagemService.editarPersonagem(ID_PERSONAGEM_ROCKIE_LEE,requestDto)).thenReturn(responseDto);
        ResponseEntity<PersonagemResponseDto> response = personagemController.editar(ID_PERSONAGEM_ROCKIE_LEE,requestDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("3- deve deletar personagem")
    @Test
    void deveDeletar(){
        Mockito.doNothing().when(personagemService).deletarPersonagem(ID_PERSONAGEM_ROCKIE_LEE);
        ResponseEntity<Void> response = personagemController.deletar(ID_PERSONAGEM_ROCKIE_LEE);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @DisplayName("4- deve listar personagens")
    @Test
    void deveListar(){
        Mockito.when(personagemService.listarPersonagens()).thenReturn(listaResponseDto);
        ResponseEntity<List<PersonagemResponseDto>> response = personagemController.listar();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("5- deve adicionar Jutsu a um personagem")
    @Test
    void deveAdicionarJutsu(){
        Mockito.doNothing().when(personagemService).adiconarJutsu(ID_PERSONAGEM_ROCKIE_LEE, ninjutsuDto);
        ResponseEntity<Void> response = personagemController.adicionarJutsu(ID_PERSONAGEM_ROCKIE_LEE, ninjutsuDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("6- deve aumentar chakra de um personagem")
    @Test
    void deveAumentarChakra(){
        Mockito.when(personagemService.aumentarChakra(ID_PERSONAGEM_ROCKIE_LEE,QUANTIDADE)).thenReturn(responseDto.getChakra());
        ResponseEntity<Integer> response = personagemController.aumentarChakra(ID_PERSONAGEM_ROCKIE_LEE,QUANTIDADE);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("7- deve atacar um personagem")
    @Test
    void deveAtacarPersonagem(){
        Mockito.when(personagemService.ataque(ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(USAR_JUTSU_FRASE_TAIJUTSU);
        ResponseEntity<String> response = personagemController.ataque(ID_PERSONAGEM_ROCKIE_LEE);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("8- deve se defender de um personagem")
    @Test
    void deveSeDefenderDeUmPersonagem(){
        Mockito.when(personagemService.defesa(ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(DESVIAR_FRASE_TAIJUTSU);
        ResponseEntity<String> response = personagemController.defesa(ID_PERSONAGEM_ROCKIE_LEE);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @DisplayName("9- deve um personagem ser derrotado")
    @Test
    void deveUmPersonagemSerDerrotado(){
        Mockito.when(personagemService.derrota(ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(DESVIAR_FRASE_TAIJUTSU);
        ResponseEntity<String> response = personagemController.derrota(ID_PERSONAGEM_ROCKIE_LEE);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
