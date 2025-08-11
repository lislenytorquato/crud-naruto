package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.enums.PersonagemEnum;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import com.crud.naruto.interfaces.Ninja;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.NinjaDeGenjutsu;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

import static com.crud.naruto.enums.PersonagemEnum.NINJA_DE_GENJUTSU;

@Service
public class PersonagemService {

    PersonagemMapper mapper = PersonagemMapper.INSTANCE;
    private final PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository){
        this.personagemRepository = personagemRepository;
    }

    public PersonagemResponseDto criarPersonagem(PersonagemRequestDto personagemRequestDto){
        Personagem personagem = mapper.requestDtoParaEntiy(personagemRequestDto);
        salvarPersonagem(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public PersonagemResponseDto editarPersonagem(Long id, PersonagemRequestDto personagemRequestDto){
        Personagem personagem = encontrarPersonagemPorId(id);
        mapper.atualizarPersonagem(personagem,personagemRequestDto);
        salvarPersonagem(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public void deletarPersonagem(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagemRepository.delete(personagem);
    }
    public List<PersonagemResponseDto> listarPersonagens(){
        List<Personagem> listaDePersonagens = personagemRepository.findAll();
        return mapper.listaEntityParaListaResponseDto(listaDePersonagens);
    }
    public Boolean adiconarJutsu(Long id, String novoJutsu){
        Personagem personagem = encontrarPersonagemPorId(id);
        boolean jutsuAdicionado = personagem.adicionarJutsu(novoJutsu);
        salvarPersonagem(personagem);
        return jutsuAdicionado;
    }
    public int aumentarChakra(Long id, int quantidade){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagem.aumentarChakra(quantidade);
        salvarPersonagem(personagem);
        return personagem.getChakra();
    }
    public String usarJutsu(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);

        if (personagem instanceof Ninja ninja){
            return ninja.usarJutsu();
        }
        throw new PersonagemNaoEncontradoException();

    }
    public String desviar(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);

        if (personagem instanceof Ninja ninja){
            return ninja.desviar();
        }
        throw new PersonagemNaoEncontradoException();


    }

    private Personagem encontrarPersonagemPorId(Long id){
        return personagemRepository.findById(id).stream().findFirst().orElseThrow(PersonagemNaoEncontradoException::new);
    }
    private void salvarPersonagem(Personagem personagem){
        personagemRepository.save(personagem);
    }
}
