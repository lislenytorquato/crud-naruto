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
import com.crud.naruto.repository.JutsuRepository;
import com.crud.naruto.repository.PersonagemRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonagemService {

    PersonagemMapper mapper = PersonagemMapper.INSTANCE;
    private final PersonagemRepository personagemRepository;
    private final JutsuRepository jutsuRepository;

    public PersonagemService(PersonagemRepository personagemRepository, JutsuRepository jutsuRepository){
        this.personagemRepository = personagemRepository;
        this.jutsuRepository = jutsuRepository;
    }

    public PersonagemResponseDto criarPersonagem(PersonagemRequestDto personagemRequestDto){
        Personagem personagem = mapper.requestDtoParaEntiy(personagemRequestDto);
        personagemRequestDto.getJutsus().forEach(jutsuDto->{
            Jutsu jutsu = mapper.jutsuDtoToJutsu(jutsuDto);
            jutsuRepository.save(jutsu);
            personagem.getJutsus().add(jutsu);
        });
        salvarPersonagem(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public PersonagemResponseDto editarPersonagem(String id, PersonagemRequestDto personagemRequestDto){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagemRequestDto.getJutsus().forEach(jutsuDto->{
            Jutsu jutsu = mapper.jutsuDtoToJutsu(jutsuDto);
            jutsuRepository.save(jutsu);
            personagem.getJutsus().add(jutsu);
        });
        personagem.setNome(personagemRequestDto.getNome());
        personagem.setVida(personagemRequestDto.getVida());
        salvarPersonagem(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public void deletarPersonagem(String id){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagemRepository.delete(personagem);
    }
    public List<PersonagemResponseDto> listarPersonagens(){
        List<Personagem> listaDePersonagens = personagemRepository.findAll();
        return mapper.listaEntityParaListaResponseDto(listaDePersonagens);
    }

    public void adiconarJutsu(String id, JutsuDto jutsuDto){
        Personagem personagem = encontrarPersonagemPorId(id);
        Jutsu jutsu = mapper.jutsuDtoToJutsu(jutsuDto);

        jutsuRepository.save(jutsu);

        personagem.adicionarJutsu(jutsu);
        salvarPersonagem(personagem);
    }

    public int aumentarChakra(String id, int quantidade){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagem.aumentarChakra(quantidade);
        salvarPersonagem(personagem);
        return personagem.getChakra();
    }

    public String ataque(String idAtacante){
        Personagem personagem = encontrarPersonagemPorId(idAtacante);
        String usouJutsu = "";

        if (personagem.getChakra()>0){
            usouJutsu = usarJutsu(idAtacante);
            chakraConsumido(personagem);
        }
        return usouJutsu + " Chakra após ataque: "+ personagem.getChakra();
    }

    public String defesa(String id){
        Personagem personagem = encontrarPersonagemPorId(id);
        String desviou = "";
        String resultado =  " Chakra após defesa: ";

        if (personagem.getChakra()>0){
            desviou = desviar(id, true);
            chakraConsumido(personagem);
            resultado = desviou + resultado + personagem.getChakra();;
            return resultado;
        }
        desviou = desviar(id,false);
        resultado = desviou + resultado + personagem.getChakra();
        return resultado;
    }

    public String derrota(String id){
        Personagem personagem = encontrarPersonagemPorId(id);
        String mensagem = "Continue jogando...";

        if (personagem.getVida() == 0 || personagem.getChakra() == 0)
            mensagem = "Personagem perdeu!!!";

        return mensagem;
    }


    private Personagem encontrarPersonagemPorId(String id){
        return personagemRepository.findById(id).orElseThrow(PersonagemNaoEncontradoException::new);
    }

    private void salvarPersonagem(Personagem personagem){
        personagemRepository.save(personagem);
    }

    private void chakraConsumido(Personagem personagem){

        personagem.getJutsus().forEach(jutsu ->{
            personagem.setChakra(personagem.getChakra() - jutsu.getConsumoDeChakra());
        });
    }

    private String usarJutsu(String id){
        Personagem personagem = encontrarPersonagemPorId(id);
        for (Jutsu jutsu : personagem.getJutsus()) {
            if (jutsu.getNome().contains("Taijutsu")) {
             return  mapper.personagemParaTaijutsu(personagem).usarJutsu();
            }
            if (jutsu.getNome().contains("Ninjutsu")) {
             return mapper.personagemParaNinjutsu(personagem).usarJutsu();
            }
        }
       throw new JutsuNaoEncontradoException();
    }

    private String desviar(String id, boolean conseguiuDesviar){
        Personagem personagem = encontrarPersonagemPorId(id);

       for (Jutsu jutsu: personagem.getJutsus()){
           if (jutsu.getNome().contains("Taijutsu")){
               return mapper.personagemParaTaijutsu(personagem).desviar(personagem,conseguiuDesviar);
           }
           if (jutsu.getNome().contains("Ninjutsu")){
               return mapper.personagemParaNinjutsu(personagem).desviar(personagem,conseguiuDesviar);
           }
       }
        throw new JutsuNaoEncontradoException();
    }


}
