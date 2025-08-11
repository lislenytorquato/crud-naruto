package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonagemService {

    PersonagemMapper mapper = PersonagemMapper.INSTANCE;
    private final PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository){
        this.personagemRepository = personagemRepository;
    }

    public PersonagemResponseDto criarPersonagem(PersonagemRequestDto personagemRequestDto){
        Personagem personagem = mapper.requestDtoParaEntiy(personagemRequestDto);
        personagemRepository.save(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public PersonagemResponseDto editarPersonagem(Long id, PersonagemRequestDto personagemRequestDto){
        Personagem personagem = personagemFindById(id);
        mapper.atualizarPersonagem(personagem,personagemRequestDto);
        personagemRepository.save(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public void deletarPersonagem(Long id){
        Personagem personagem = personagemFindById(id);
        personagemRepository.delete(personagem);
    }

    private Personagem personagemFindById(Long id){
        return personagemRepository.findById(id).stream().findFirst().orElseThrow(PersonagemNaoEncontradoException::new);
    }
}
