package com.crud.naruto.service;

import com.crud.naruto.dto.JutsuDto;
import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.exception.JutsuNaoEncontradoException;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
import com.crud.naruto.interfaces.Ninja;
import com.crud.naruto.mapper.PersonagemMapper;
import com.crud.naruto.model.Jutsu;
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
    public void adiconarJutsu(Long id, String nomeJutsu, Jutsu jutsu){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagem.adicionarJutsu(nomeJutsu, jutsu);
        salvarPersonagem(personagem);
    }
    public int aumentarChakra(Long id, int quantidade){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagem.aumentarChakra(quantidade);
        salvarPersonagem(personagem);
        return personagem.getChakra();
    }
    private String usarJutsu(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);

        if (personagem.getJutsus().containsKey("Taijutsu")){
            NinjaDeTaijutsu ninjaDeTaijutsu = mapper.personagemParaTaijutsu(personagem);
             return ninjaDeTaijutsu.usarJutsu();
        }else if (personagem.getJutsus().containsKey("Ninjutsu")){
            NinjaDeNinjutsu ninjaDeNinjutsu = mapper.personagemParaNinjutsu(personagem);
            return ninjaDeNinjutsu.usarJutsu();
        }else {
            throw new JutsuNaoEncontradoException();
        }
    }
    private String desviar(Long id, boolean conseguiuDesviar){
        Personagem personagem = encontrarPersonagemPorId(id);

        if (personagem.getJutsus().containsKey("Taijutsu")){
            NinjaDeTaijutsu ninjaDeTaijutsu = mapper.personagemParaTaijutsu(personagem);
            return ninjaDeTaijutsu.desviar(personagem,conseguiuDesviar);
        }else if (personagem.getJutsus().containsKey("Ninjutsu")){
            NinjaDeNinjutsu ninjaDeNinjutsu = mapper.personagemParaNinjutsu(personagem);
            return ninjaDeNinjutsu.desviar(personagem,conseguiuDesviar);
        }else {
            throw new JutsuNaoEncontradoException();
        }

    }

    public String ataque(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);
        int chakraConsumido = 0;

        while (personagem.getChakra()>0){
            usarJutsu(id);
            chakraConsumido = chakraConsumido(personagem,chakraConsumido);
        }
        return "Chakra após ataque: "+ personagem.getChakra();
    }
    public String defesa(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);
        int chakraConsumido = 0;

        while (personagem.getChakra()>0){
            desviar(id,true);
            chakraConsumido(personagem,chakraConsumido);
        }
        desviar(id,false);

        return "Chakra após defesa: "+ chakraConsumido;
    }

    public String personagemDerrotado(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);
        if (personagem.getVida() == 0 || personagem.getChakra() == 0){
            return "Personagem perdeu!!!";
        }
    }


    private Personagem encontrarPersonagemPorId(Long id){
        return personagemRepository.findById(id).orElseThrow(PersonagemNaoEncontradoException::new);
    }
    private void salvarPersonagem(Personagem personagem){
        personagemRepository.save(personagem);
    }
    private int chakraConsumido(Personagem personagem, int chakraConsumido){
        chakraConsumido = personagem.getChakra() - personagem.getJutsus().values().stream().findFirst().orElseThrow().getConsumoDeChakra();
        personagem.setChakra(chakraConsumido);
    }


}
