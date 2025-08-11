package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.helper.AssertionsHelper;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.crud.naruto.helper.TestHelper.ID_PERSONAGEM_ROCKIE_LEE;
import static com.crud.naruto.helper.TestHelper.criarPersonagemRockieLee;
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

        Mockito.when(personagemRepository.save(any(Personagem.class))).thenReturn(personagem);

        PersonagemResponseDto responseDto = mapper.entityParaResponseDto(personagem);

        PersonagemResponseDto response = personagemService.criarPersonagem(requestDto);

        AssertionsHelper.assertEqualsParaResponseEPersonagem(response,responseDto);

    }

    @DisplayName("2- Deve editar um personagem")
    @Test
    void deveEditarUmPersonagem(){
        Optional<Personagem> personagem = Optional.of(criarPersonagemRockieLee());

        Mockito.when(personagemRepository.findById(ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(personagem);

        mapper.atualizarPersonagem(personagem.get(),requestDto);

        PersonagemResponseDto responseDto = mapper.entityParaResponseDto(personagem.get());

        PersonagemResponseDto response = personagemService.editarPersonagem(ID_PERSONAGEM_ROCKIE_LEE,requestDto);

        AssertionsHelper.assertEqualsParaResponseEPersonagem(response,responseDto);
    }

    @DisplayName("3- Deve deletar um personagem")
    @Test
    void deveDeletarUmPersonagem(){
        Optional<Personagem> personagem = Optional.of(criarPersonagemRockieLee());

        Mockito.when(personagemRepository.findById(ID_PERSONAGEM_ROCKIE_LEE)).thenReturn(personagem);

        Mockito.doNothing().when(personagemRepository).delete(personagem.get());

        personagemService.deletarPersonagem(ID_PERSONAGEM_ROCKIE_LEE);

        Mockito.verify(personagemRepository,Mockito.atMost(1)).delete(personagem.get());

    }

}
