package com.crud.naruto.mapper;

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

import java.util.List;

@Mapper
public interface PersonagemMapper {

    PersonagemMapper INSTANCE = Mappers.getMapper(PersonagemMapper.class);

    Personagem requestDtoParaEntiy(PersonagemRequestDto personagemRequestDto);
    PersonagemResponseDto entityParaResponseDto(Personagem personagem);
    void atualizarPersonagem(@MappingTarget Personagem personagem, PersonagemRequestDto personagemRequestDto);
    List<PersonagemResponseDto> listaEntityParaListaResponseDto(List<Personagem> personagens);
    NinjaDeNinjutsu personagemParaNinjutsu(Personagem personagem);
    NinjaDeTaijutsu personagemParaTaijutsu(Personagem personagem);

}
