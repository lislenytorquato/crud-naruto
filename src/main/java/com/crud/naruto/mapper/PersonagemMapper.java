package com.crud.naruto.mapper;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Personagem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonagemMapper {

    PersonagemMapper INSTANCE = Mappers.getMapper(PersonagemMapper.class);

    Personagem requestDtoParaEntiy(PersonagemRequestDto personagemRequestDto);
    PersonagemResponseDto entityParaResponseDto(Personagem personagem);
    void atualizarPersonagem(@MappingTarget Personagem personagem, PersonagemRequestDto personagemRequestDto);
}
