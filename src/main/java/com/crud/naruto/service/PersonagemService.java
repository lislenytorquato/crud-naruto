package com.crud.naruto.service;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import com.crud.naruto.interfaces.Ninja;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.NinjaDeGenjutsu;
import com.crud.naruto.model.NinjaDeNinjutsu;
import com.crud.naruto.model.NinjaDeTaijutsu;
import com.crud.naruto.model.Personagem;
import com.crud.naruto.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean adiconarJutsu(Long id, String novoJutsu){
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

        if (personagem.getJutsus().contains("Genjutsu")){
            NinjaDeGenjutsu ninjaDeGenjutsu = mapper.personagemParaGenjutsu(personagem);
            ninjaDeGenjutsu.usarJutsu();
        }else if (personagem.getJutsus().contains("Taijutsu")){
            NinjaDeTaijutsu ninjaDeTaijutsu = mapper.personagemParaTaijutsu(personagem);
            ninjaDeTaijutsu.usarJutsu();
        }else if (personagem.getJutsus().contains("Ninjutsu")){
            NinjaDeNinjutsu ninjaDeNinjutsu = mapper.personagemParaNinjutsu(personagem);
            ninjaDeNinjutsu.usarJutsu();
        }

        throw new PersonagemNaoEncontradoException();

    }
    public String desviar(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);

        if (personagem.getJutsus().contains("Genjutsu")){
            NinjaDeGenjutsu ninjaDeGenjutsu = mapper.personagemParaGenjutsu(personagem);
            ninjaDeGenjutsu.desviar();
        }else if (personagem.getJutsus().contains("Taijutsu")){
            NinjaDeTaijutsu ninjaDeTaijutsu = mapper.personagemParaTaijutsu(personagem);
            ninjaDeTaijutsu.desviar();
        }else if (personagem.getJutsus().contains("Ninjutsu")){
            NinjaDeNinjutsu ninjaDeNinjutsu = mapper.personagemParaNinjutsu(personagem);
            ninjaDeNinjutsu.desviar();
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
