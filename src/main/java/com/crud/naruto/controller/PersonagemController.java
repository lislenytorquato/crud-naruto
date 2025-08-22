package com.crud.naruto.controller;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.model.Jutsu;
import com.crud.naruto.service.PersonagemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personagem")
public class PersonagemController {

    @Autowired
    PersonagemService personagemService;

    @ApiResponse(description = "Criar personagem", responseCode = "201")
    @PostMapping
    ResponseEntity<PersonagemResponseDto> criar(@Valid @RequestBody PersonagemRequestDto personagemRequestDto){
        PersonagemResponseDto responseDto = personagemService.criarPersonagem(personagemRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @ApiResponse(description = "Editar personagem", responseCode = "200")
    @PutMapping("/{id}")
    ResponseEntity<PersonagemResponseDto> editar(@PathVariable Long id,@Valid @RequestBody PersonagemRequestDto personagemRequestDto){
        PersonagemResponseDto responseDto = personagemService.editarPersonagem(id, personagemRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @ApiResponse(description = "Deletar personagem", responseCode = "204")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable Long id){
        personagemService.deletarPersonagem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiResponse(description = "Listar todos personagens", responseCode = "200")
    @GetMapping
    ResponseEntity<List<PersonagemResponseDto>> listar(){
        List<PersonagemResponseDto> responseDto = personagemService.listarPersonagens();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @ApiResponse(description = "Adicionar jutsu ao personagem", responseCode = "200")
    @PutMapping("{id}/adiciona-jutsu")
    ResponseEntity<Boolean> adicionarJutsu(@PathVariable Long id, @Valid @RequestBody String nomeJutsu, @Valid @RequestBody Jutsu jutsu){
        personagemService.adiconarJutsu(id,nomeJutsu, jutsu);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponse(description = "Aumenta chakra do personagem", responseCode = "200")
    @PutMapping("{id}/aumenta-chakra")
    ResponseEntity<Integer> aumentarChakra(@PathVariable Long id, @Valid @RequestBody int quantidade){
        int chakraAumentado = personagemService.aumentarChakra(id, quantidade);
        return new ResponseEntity<>(chakraAumentado,HttpStatus.OK);
    }

    @ApiResponse(description = "Usar jutsu do personagem", responseCode = "200")
    @GetMapping("{id}/usa-jutsu")
    ResponseEntity<String> usarJutsu(@PathVariable Long id){
        String jutsuUsado = personagemService.usarJutsu(id);
        return new ResponseEntity<>(jutsuUsado,HttpStatus.OK);
    }

    @ApiResponse(description = "Desvia do personagem", responseCode = "200")
    @GetMapping("{id}/desvia")
    ResponseEntity<String> desviar(@PathVariable Long id){
        String desvio = personagemService.desviar(id);
        return new ResponseEntity<>(desvio,HttpStatus.OK);
    }
}
