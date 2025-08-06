package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.helper.AssertionsHelper;
import com.crud.naruto.helper.TestHelper;
import com.crud.naruto.mapper.PersonagemMapper;
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

        requestDto = TestHelper.criarRequestDto();
        responseDto= TestHelper.criarResponseDto();
    }

    @DisplayName("Deve criar um personagem")
    @Test
    void deveCriarUmPersonagem(){
        Personagem personagem = mapper.requestDtoParaEntiy(requestDto);
        Mockito.when(personagemRepository.save(personagem)).thenReturn(personagem);
        PersonagemResponseDto response = personagemService.criarPersonagem(requestDto);

        Assertions.assertEquals(response.getNome(),personagem.getNome());
        Assertions.assertEquals(response.getIdade(),personagem.getIdade());
        Assertions.assertEquals(response.getAldeia(),personagem.getAldeia());
        Assertions.assertEquals(response.getJutsus(),personagem.getJutsus());
        Assertions.assertEquals(response.getChakra(),personagem.getChakra());
    }

}
