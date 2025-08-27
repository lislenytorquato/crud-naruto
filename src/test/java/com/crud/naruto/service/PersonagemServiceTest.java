package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import com.crud.naruto.helper.AssertionsHelper;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.JutsuRepository;
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
        personagemService.adiconarJutsu(idRockieLee,ninjutsuDto);

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

    @DisplayName("7- Deve atacar um personagem ")
    @Order(1)
    @ParameterizedTest
    @MethodSource("geraPersonagensAtacando")
    void deveAtacarUmPersonagem(Long id,Personagem personagem,String frase, String nomeJutsu, int chakraConsumido){
        mockEncontrarPorId(id,personagem);
        String response = personagemService.ataque(id);

        Assertions.assertEquals(frase+CHAKRA_CONSUMIDO_ATAQUE_FRASE+chakraConsumido,response);
        Assertions.assertEquals(25, personagem.getJutsus().get(nomeJutsu).getDano());
        Assertions.assertEquals(10,personagem.getJutsus().get(nomeJutsu).getConsumoDeChakra());
    }

    @DisplayName("8- Deve se defender de um personagem quando conseguiuDesviar é true")
    @Order(2)
    @ParameterizedTest
    @MethodSource("geraPersonagensQueConseguem")
    void deveSeDefenderDePersonagemQuandoConseguiuDesviarEhTrue(Long id,Personagem personagem,String frase, int chakraConsumido){
        mockEncontrarPorId(id,personagem);
        String response = personagemService.defesa(id);

        Assertions.assertEquals(response,frase+CHAKRA_CONSUMIDO_DEFESA_FRASE+chakraConsumido);
    }
    @DisplayName("9- Deve se defender de um personagem quando conseguiuDesviar é false")
    @Order(2)
    @ParameterizedTest
    @MethodSource("geraPersonagensNaoDesviando")
    void deveDesviarDePersonagemQuandoConseguiuDesviarEhFalse(Long id,Personagem personagem,String frase,int chakraConsumido){
        mockEncontrarPorId(id,personagem);
        String response = personagemService.defesa(id);

        Assertions.assertEquals(response,NAO_DESVIEI_FRASE+personagem.getVida()+CHAKRA_CONSUMIDO_DEFESA_FRASE+chakraConsumido);
        Assertions.assertTrue(response.contains(Integer.toString(personagem.getVida())));
    }
    @DisplayName("10- Deve continuar jogando quando vida é maior que 0 e chakra é maior que 0")
    @Order(2)
    @ParameterizedTest
    @MethodSource("geraPersonagensQueConseguem")
    void deveContinuarJogandoQuandoVidaEhMaiorQueZeroEChakraEhMaiorQueZero(Long id,Personagem personagem,String frase,int chakraConsumido){
        mockEncontrarPorId(id,personagem);
        String response = personagemService.derrota(id);

        Assertions.assertEquals(CONTINUE_JOGANDO_FRASE, response);
    }

    @DisplayName("11- Deve ser derrotado quando vida é igual a 0 e chakra é igual a 0")
    @Order(2)
    @ParameterizedTest
    @MethodSource("geraPersonagensSemVidaESemChakra")
    void deveSerDerrotadoQuandoVidaEhMaiorQueZeroEChakraEhMaiorQueZero(Long id,Personagem personagem,String frase){
        mockEncontrarPorId(id,personagem);
        String response = personagemService.derrota(id);

        Assertions.assertEquals(PERDEU_FRASE, response);
    }

    @DisplayName("12- Deve lancar excecao para personagem nao encontrado ")
    @Test
    @Order(9)
    void deveLancarExcecaoPersonagemNaoEncontrado(){

        Assertions.assertThrows(PersonagemNaoEncontradoException.class,()->personagemService.ataque(4L));
    }

    @DisplayName("13- Deve lancar excecao para Jutsu nao encontrado ")
    @Test
    @Order(10)
    void deveLancarExcecaoJutsuNaoEncontrado(){

        personagemRockieLee.getJutsus().remove(NOME_TAIJUTSU);
        Assertions.assertThrows(PersonagemNaoEncontradoException.class,()->personagemService.ataque(ID_PERSONAGEM_ROCKIE_LEE));
    }

    private void mockEncontrarPorId(Long id, Personagem personagem){
        Mockito.when(personagemRepository.findById(id)).thenReturn(Optional.of(personagem));
    }
    private void mockSalvarPersonagem(Personagem personagem){
        Mockito.when(personagemRepository.save(any(Personagem.class))).thenReturn(personagem);
    }
    private static Stream<Arguments> geraPersonagensAtacando(){
        int chakraConsumidoNaruto = personagemNaruto.getChakra() - CONSUMO_CHAKRA_NINJUTSU;
        int chakraConsumidoRockieLee = personagemRockieLee.getChakra() - CONSUMO_CHAKRA_TAIJUTSU;
        return Stream.of(
                Arguments.of(idNaruto,personagemNaruto, USAR_JUTSU_FRASE_NINJUTSU,NOME_NINJUTSU,chakraConsumidoNaruto),
                Arguments.of(idRockieLee,personagemRockieLee,USAR_JUTSU_FRASE_TAIJUTSU,NOME_TAIJUTSU,chakraConsumidoRockieLee)
        );
    }
    private static Stream<Arguments> geraPersonagensQueConseguem(){
        return Stream.of(
                Arguments.of(idNaruto,personagemNaruto, DESVIAR_FRASE_NINJUTSU, personagemNaruto.getChakra()),
                Arguments.of(idRockieLee,personagemRockieLee,DESVIAR_FRASE_TAIJUTSU, personagemRockieLee.getChakra())
        );
    }
    private static Stream<Arguments> geraPersonagensNaoDesviando(){
        int chakraConsumidoNaruto = 0;
        int chakraConsumidoRockieLee =0;
        return Stream.of(
                Arguments.of(idNaruto,criarPersonagemNarutoSemChakra(), NAO_DESVIEI_FRASE,chakraConsumidoNaruto),
                Arguments.of(idRockieLee,criarPersonagemRockieLeeSemChakra(),NAO_DESVIEI_FRASE,chakraConsumidoRockieLee)
        );
    }
    private static Stream<Arguments> geraPersonagensSemVidaESemChakra(){
        return Stream.of(
                Arguments.of(idNaruto,criarPersonagemNarutoSemVidaESemChakra(), NAO_DESVIEI_FRASE),
                Arguments.of(idRockieLee,criarPersonagemRockieLeeSemVidaESemChakra(),NAO_DESVIEI_FRASE)
        );
    }
}
