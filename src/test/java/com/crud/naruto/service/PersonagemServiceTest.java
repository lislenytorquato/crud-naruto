package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.helper.AssertionsHelper;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.interfaces.Ninja;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.NinjaDeTaijutsu;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.crud.naruto.helper.TestHelper.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PersonagemServiceTest {

    private static PersonagemRequestDto requestDto;
    private static PersonagemResponseDto responseDto;

    @InjectMocks
    PersonagemService personagemService;

    @Mock
    PersonagemRepository personagemRepository;

    PersonagemMapper mapper = PersonagemMapper.INSTANCE;

    @BeforeAll
    static void setUp(){

        requestDto = TestHelper.criarRockieLeeRequestDto();
       // responseDto= TestHelper.criarRockieLeeResponseDto();
    }

    @DisplayName("1- Deve criar um personagem")
    @Test
    void deveCriarUmPersonagem(){
        Personagem personagem = mapper.requestDtoParaEntiy(requestDto);

        mockSalvarPersonagem(personagem);

        PersonagemResponseDto responseDto = mapper.entityParaResponseDto(personagem);

        PersonagemResponseDto response = personagemService.criarPersonagem(requestDto);

        AssertionsHelper.assertEqualsParaCompararComResponse(response,responseDto);

    }

    @DisplayName("2- Deve editar um personagem")
    @Test
    void deveEditarUmPersonagem(){
        Optional<Personagem> personagem = Optional.of(criarPersonagemRockieLee());

        mockEncontrarPorId();

        mapper.atualizarPersonagem(personagem.get(),requestDto);

        mockSalvarPersonagem(personagem.get());

        PersonagemResponseDto responseDto = mapper.entityParaResponseDto(personagem.get());

        PersonagemResponseDto response = personagemService.editarPersonagem(ID_PERSONAGEM_ROCKIE_LEE,requestDto);

        AssertionsHelper.assertEqualsParaCompararComResponse(response,responseDto);
    }

    @DisplayName("3- Deve deletar um personagem")
    @Test
    void deveDeletarUmPersonagem(){
        Optional<Personagem> personagem = Optional.of(criarPersonagemRockieLee());

        mockEncontrarPorId();

        Mockito.doNothing().when(personagemRepository).delete(any(Personagem.class));

        personagemService.deletarPersonagem(ID_PERSONAGEM_ROCKIE_LEE);

        Mockito.verify(personagemRepository,Mockito.atMost(1)).delete(personagem.get());

    }

    @DisplayName("4- Deve listar todos os personagens")
    @Test
    void deveListarPersonagens(){
        List<Personagem> listaDePersonagens = List.of(criarPersonagemRockieLee());

        Mockito.when(personagemRepository.findAll()).thenReturn(listaDePersonagens);

        List<PersonagemResponseDto> listaResponseDto = mapper.listaEntityParaListaResponseDto(listaDePersonagens);

        List<PersonagemResponseDto> listaResponse = personagemService.listarPersonagens();

        Assertions.assertEquals(listaResponseDto.size(),listaResponse.size());
        AssertionsHelper.assertEqualsParaCompararComResponse(listaResponse.get(0),listaResponseDto.get(0));
    }

    @DisplayName("5- Deve adicionar o jutsu de um personagem ")
    @Test
    void deveAdicionarJutsuDePersonagem(){
        Optional<Personagem> personagem = Optional.of(criarPersonagemRockieLee());
        mockEncontrarPorId();
        mockSalvarPersonagem(personagem.get());
        Boolean jutsuAdicionado = personagemService.adiconarJutsu(ID_PERSONAGEM_ROCKIE_LEE, JUTSU_NOVO);

        Assertions.assertTrue(jutsuAdicionado);
    }

    @DisplayName("6- Deve aumentar o chakra de um personagem ")
    @Test
    void deveAumentarChakraDePersonagem(){
        Optional<Personagem> personagem = Optional.of(criarPersonagemRockieLee());
        mockEncontrarPorId();
        mockSalvarPersonagem(personagem.get());
        int chakraAumentado = personagemService.aumentarChakra(ID_PERSONAGEM_ROCKIE_LEE, QUANTIDADE);

        Assertions.assertNotEquals(CHAKRA_PERSONAGEM_ROCKIE_LEE,chakraAumentado);
    }

    @DisplayName("7- Deve usar jutsu de um personagem ")
    @Test
    void deveUsarJutsuDePersonagem(){
        Mockito.when(personagemRepository.findById(TestHelper.ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(Optional.of(criarPersonagemRockieLeeNinjaDeTaijutsu()));
        NinjaDeTaijutsu ninjaDeTaijutsu = new NinjaDeTaijutsu(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,IDADE_PERSONAGEM_ROCKIE_LEE,ALDEIA_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE);
        String jutsuUsado = ninjaDeTaijutsu.usarJutsu();
        String jutsuUsadoResponse = personagemService.usarJutsu(ID_PERSONAGEM_ROCKIE_LEE);

        Assertions.assertEquals(jutsuUsado,jutsuUsadoResponse);
    }

    @DisplayName("8- Deve desviar de um personagem ")
    @Test
    void deveDesviarDeUmPersonagem(){
        Mockito.when(personagemRepository.findById(TestHelper.ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(Optional.of(criarPersonagemRockieLeeNinjaDeTaijutsu()));
        NinjaDeTaijutsu ninjaDeTaijutsu = new NinjaDeTaijutsu(ID_PERSONAGEM_ROCKIE_LEE,NOME_PERSONAGEM_ROCKIE_LEE,IDADE_PERSONAGEM_ROCKIE_LEE,ALDEIA_PERSONAGEM_ROCKIE_LEE,JUTSUS_PERSONAGEM_ROCKIE_LEE,CHAKRA_PERSONAGEM_ROCKIE_LEE);
        String desvioUsado = ninjaDeTaijutsu.desviar();
        String desvioUsadoResponse = personagemService.desviar(ID_PERSONAGEM_ROCKIE_LEE);

        Assertions.assertEquals(desvioUsado,desvioUsadoResponse);
    }

    private void mockEncontrarPorId(){
        Mockito.when(personagemRepository.findById(TestHelper.ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(Optional.of(criarPersonagemRockieLee()));
    }
    private void mockSalvarPersonagem(Personagem personagem){
        Mockito.when(personagemRepository.save(any(Personagem.class))).thenReturn(personagem);
    }
}
