package com.crud.naruto.service;

import com.crud.naruto.dto.JutsuDto;
import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.exception.JutsuNaoEncontradoException;
import com.crud.naruto.exception.PersonagemNaoEncontradoException;
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
        personagemRequestDto.getJutsus().forEach((nome,jutsuDto)->{
            Jutsu jutsu = mapper.jutsuDtoToJutsu(jutsuDto);
            jutsuRepository.save(jutsu);
            personagem.getJutsus().put(nome,jutsu);
        });
        salvarPersonagem(personagem);
        return mapper.entityParaResponseDto(personagem);
    }

    public PersonagemResponseDto editarPersonagem(Long id, PersonagemRequestDto personagemRequestDto){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagemRequestDto.getJutsus().forEach((nome,jutsuDto)->{
            Jutsu jutsu = mapper.jutsuDtoToJutsu(jutsuDto);
            jutsuRepository.save(jutsu);
            personagem.getJutsus().put(nome,jutsu);
        });
        personagem.setNome(personagemRequestDto.getNome());
        personagem.setVida(personagemRequestDto.getVida());
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
    public void adiconarJutsu(Long id, JutsuDto jutsuDto){
        Personagem personagem = encontrarPersonagemPorId(id);
        Jutsu jutsu = mapper.jutsuDtoToJutsu(jutsuDto);

        jutsuRepository.save(jutsu);

        personagem.adicionarJutsu(jutsu.getNome(), jutsu);
        salvarPersonagem(personagem);
    }
    public int aumentarChakra(Long id, int quantidade){
        Personagem personagem = encontrarPersonagemPorId(id);
        personagem.aumentarChakra(quantidade);
        salvarPersonagem(personagem);
        return personagem.getChakra();
    }

    public String ataque(Long idAtacante){
        Personagem personagem = encontrarPersonagemPorId(idAtacante);
        String usouJutsu = "";

        if (personagem.getChakra()>0){
            usouJutsu = usarJutsu(idAtacante);
            chakraConsumido(personagem);
        }
        return usouJutsu + " Chakra após ataque: "+ personagem.getChakra();
    }
    public String defesa(Long id){
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

    public String derrota(Long id){
        Personagem personagem = encontrarPersonagemPorId(id);
        String mensagem = "Continue jogando...";

        if (personagem.getVida() == 0 || personagem.getChakra() == 0)
            mensagem = "Personagem perdeu!!!";

        return mensagem;

    }


    private Personagem encontrarPersonagemPorId(Long id){
        return personagemRepository.findById(id).orElseThrow(PersonagemNaoEncontradoException::new);
    }
    private void salvarPersonagem(Personagem personagem){
        personagemRepository.save(personagem);
    }
    private void chakraConsumido(Personagem personagem){

                personagem.getJutsus().forEach((nome,jutsu)->{
            personagem.setChakra(personagem.getChakra() - personagem.getJutsus().get(nome).getConsumoDeChakra());
        });
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


}
