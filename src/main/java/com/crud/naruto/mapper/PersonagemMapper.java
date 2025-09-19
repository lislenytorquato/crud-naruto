package com.crud.naruto.mapper;

import com.crud.naruto.dto.AldeiaDto;
import com.crud.naruto.dto.JutsuDto;
import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Jutsu;
import com.crud.naruto.model.NinjaDeNinjutsu;
import com.crud.naruto.model.NinjaDeTaijutsu;
import com.crud.naruto.model.Personagem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PersonagemMapper {

    PersonagemMapper INSTANCE = Mappers.getMapper(PersonagemMapper.class);

    Personagem requestDtoParaEntiy(PersonagemRequestDto personagemRequestDto);
    default PersonagemResponseDto entityParaResponseDto(Personagem personagem, AldeiaDto aldeiaDto){
        JutsuDto jutsuDto = new JutsuDto();
        List<JutsuDto> jutsus = new ArrayList<>();

        personagem.getJutsus().forEach(jutsu -> {
            jutsuDto.setNome(jutsu.getNome());
            jutsuDto.setDano(jutsu.getDano());
            jutsuDto.setConsumoDeChakra(jutsu.getConsumoDeChakra());
            jutsus.add(jutsuDto);
        });

        return PersonagemResponseDto.builder()
                .nome(personagem.getNome())
                .jutsus(jutsus)
                .vida(personagem.getVida())
                .chakra(personagem.getChakra())
                .aldeiaDto(aldeiaDto).build();
    }
    void atualizarPersonagem(@MappingTarget Personagem personagem, PersonagemRequestDto personagemRequestDto);
    default List<PersonagemResponseDto> listaEntityParaListaResponseDto(List<Personagem> personagens, List<AldeiaDto> aldeiaDtos){
        List<PersonagemResponseDto> responseDtos = new ArrayList<>();
        PersonagemResponseDto responseDto = new PersonagemResponseDto();
        List<JutsuDto> jutsuDtos = new ArrayList<>();

            personagens.forEach(personagem -> {
                responseDto.setNome(personagem.getNome());

                personagem.getJutsus().forEach(jutsu -> {
                    jutsuDtos.add(jutsuToJutsuDto(jutsu));
                    responseDto.setJutsus(jutsuDtos);
                });

                responseDto.setVida(personagem.getVida());
                responseDto.setChakra(personagem.getChakra());

                responseDtos.add(responseDto);
            });
            aldeiaDtos.forEach(aldeiaDto -> {
                responseDto.setAldeiaDto(aldeiaDto);
                responseDtos.add(responseDto);
            });
            return responseDtos;
    }
    NinjaDeNinjutsu personagemParaNinjutsu(Personagem personagem);
    NinjaDeTaijutsu personagemParaTaijutsu(Personagem personagem);
    Jutsu jutsuDtoToJutsu(JutsuDto jutsuDto);
    JutsuDto jutsuToJutsuDto(Jutsu jutsu);

}
