package com.crud.naruto.dto;

import com.crud.naruto.model.Jutsu;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class PersonagemRequestDto {
    @NotBlank
    private String nome;
    @NotEmpty
    private Map<String, Jutsu> jutsus;
    @Max(100)
    private int vida;
}
