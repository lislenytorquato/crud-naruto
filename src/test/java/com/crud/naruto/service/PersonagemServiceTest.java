package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import com.crud.naruto.helper.AssertionsHelper;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.crud.naruto.helper.TestHelper.*;
import static org.mockito.ArgumentMatchers.any;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class PersonagemServiceTest {

    private static PersonagemRequestDto requestDto;
    private static Personagem personagemRockieLee;
    private static Long idRockieLee;
    private static Personagem personagemNaruto;
    private static Long idNaruto;
    private static Personagem personagemSakura;
    private static Long idSakura;

    @InjectMocks
    PersonagemService personagemService;

    @Mock
    PersonagemRepository personagemRepository;

    PersonagemMapper mapper = PersonagemMapper.INSTANCE;

    @BeforeAll
    static void setUp(){

         personagemRockieLee = criarPersonagemRockieLee();
         idRockieLee = ID_PERSONAGEM_ROCKIE_LEE;
         personagemNaruto = criarPersonagemNaruto();
         idNaruto = ID_PERSONAGEM_NARUTO;
         personagemSakura = criarPersonagemSakura();
         idSakura = ID_PERSONAGEM_SAKURA;

        requestDto = TestHelper.criarRockieLeeRequestDto();
    }

    @DisplayName("1- Deve criar um personagem")
    @Test
    @Order(5)
    void deveCriarUmPersonagem(){
        Personagem personagem = mapper.requestDtoParaEntiy(requestDto);

        mockSalvarPersonagem(personagem);

        PersonagemResponseDto responseDto = mapper.entityParaResponseDto(personagem);

        PersonagemResponseDto response = personagemService.criarPersonagem(requestDto);

        AssertionsHelper.assertEqualsParaCompararComResponse(response,responseDto,NOME_TAIJUTSU);

    }

    @DisplayName("2- Deve editar um personagem")
    @Test
    @Order(6)
    void deveEditarUmPersonagem(){

        mockEncontrarPorId(idRockieLee,personagemRockieLee);

        mapper.atualizarPersonagem(personagemRockieLee,requestDto);

        mockSalvarPersonagem(personagemRockieLee);

        PersonagemResponseDto responseDto = mapper.entityParaResponseDto(personagemRockieLee);

        PersonagemResponseDto response = personagemService.editarPersonagem(idRockieLee,requestDto);

        AssertionsHelper.assertEqualsParaCompararComResponse(response,responseDto,NOME_TAIJUTSU);
    }

    @DisplayName("3- Deve deletar um personagem")
    @Test
    @Order(8)
    void deveDeletarUmPersonagem(){

        mockEncontrarPorId(idRockieLee,personagemRockieLee);

        Mockito.doNothing().when(personagemRepository).delete(any(Personagem.class));

        personagemService.deletarPersonagem(idRockieLee);

        Mockito.verify(personagemRepository,Mockito.atMost(1)).delete(personagemRockieLee);

    }

    @DisplayName("4- Deve listar todos os personagens")
    @Test
    @Order(4)
    void deveListarPersonagens(){
        List<Personagem> listaDePersonagens = List.of(personagemRockieLee);

        Mockito.when(personagemRepository.findAll()).thenReturn(listaDePersonagens);

        List<PersonagemResponseDto> listaResponseDto = mapper.listaEntityParaListaResponseDto(listaDePersonagens);

        List<PersonagemResponseDto> listaResponse = personagemService.listarPersonagens();

        Assertions.assertEquals(listaResponseDto.size(),listaResponse.size());
        AssertionsHelper.assertEqualsParaCompararComResponse(listaResponse.get(0),listaResponseDto.get(0), NOME_TAIJUTSU);
    }

    @DisplayName("5- Deve adicionar o jutsu de um personagem ")
    @Test
    @Order(7)
    void deveAdicionarJutsuDePersonagem(){

        mockEncontrarPorId(idRockieLee,personagemRockieLee);
        mockSalvarPersonagem(personagemRockieLee);
        personagemService.adiconarJutsu(idRockieLee, NOME_NINJUTSU,ninjutsu);

        AssertionsHelper.assertParaAdicionarJutsu(personagemRockieLee, NOME_NINJUTSU,ninjutsu);
    }

    @DisplayName("6- Deve aumentar o chakra de um personagem ")
    @Test
    @Order(3)
    void deveAumentarChakraDePersonagem(){

        mockEncontrarPorId(idRockieLee,personagemRockieLee);
        mockSalvarPersonagem(personagemRockieLee);
        int chakraAumentado = personagemService.aumentarChakra(idRockieLee, QUANTIDADE);

        Assertions.assertNotEquals(CHAKRA_PERSONAGEM_ROCKIE_LEE,chakraAumentado);
    }

    @DisplayName("7- Deve usar jutsu de um personagem ")
    @Order(1)
    @ParameterizedTest
    @MethodSource("geraPersonagensUsandoJutsu")
    void deveUsarJutsuDePersonagem(Long id,Personagem personagem,String frase){
        mockEncontrarPorId(id,personagem);
        String jutsuUsadoResponse = personagemService.usarJutsu(id);

        Assertions.assertEquals(frase,jutsuUsadoResponse);
    }

    @DisplayName("8- Deve desviar de um personagem ")
    @Order(2)
    @ParameterizedTest
    @MethodSource("geraPersonagensDesviando")
    void deveDesviarDePersonagem(Long id,Personagem personagem,String frase){
        mockEncontrarPorId(id,personagem);
        String desvioResponse = personagemService.desviar(id);

        Assertions.assertEquals(frase,desvioResponse);
    }

    @DisplayName("9- Deve lancar excecao para personagem nao encontrado ")
    @Test
    @Order(9)
    void deveLancarExcecaoPersonagemNaoEncontrado(){

        Assertions.assertThrows(PersonagemNaoEncontradoException.class,()->personagemService.usarJutsu(4L));
    }

    @DisplayName("10- Deve lancar excecao para Jutsu nao encontrado ")
    @Test
    @Order(10)
    void deveLancarExcecaoJutsuNaoEncontrado(){

        personagemRockieLee.getJutsus().remove(NOME_TAIJUTSU);
        Assertions.assertThrows(PersonagemNaoEncontradoException.class,()->personagemService.usarJutsu(ID_PERSONAGEM_ROCKIE_LEE));
    }

    private void mockEncontrarPorId(Long id, Personagem personagem){
        Mockito.when(personagemRepository.findById(id)).thenReturn(Optional.of(personagem));
    }
    private void mockSalvarPersonagem(Personagem personagem){
        Mockito.when(personagemRepository.save(any(Personagem.class))).thenReturn(personagem);
    }
    private static Stream<Arguments> geraPersonagensUsandoJutsu(){
        return Stream.of(
                Arguments.of(idNaruto,personagemNaruto, USAR_JUTSU_FRASE_NINJUTSU),
                Arguments.of(idRockieLee,personagemRockieLee,USAR_JUTSU_FRASE_TAIJUTSU)
        );
    }
    private static Stream<Arguments> geraPersonagensDesviando(){
        return Stream.of(
                Arguments.of(idNaruto,personagemNaruto, DESVIAR_FRASE_NINJUTSU),
                Arguments.of(idRockieLee,personagemRockieLee,DESVIAR_FRASE_TAIJUTSU)
        );
    }
}
