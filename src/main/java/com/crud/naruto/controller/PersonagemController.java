package com.crud.naruto.controller;

import com.crud.naruto.dto.PersonagemRequestDto;
import com.crud.naruto.dto.PersonagemResponseDto;
import com.crud.naruto.service.PersonagemService;
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

    @PostMapping
    ResponseEntity<PersonagemResponseDto> criar(@RequestBody PersonagemRequestDto personagemRequestDto){
        PersonagemResponseDto responseDto = personagemService.criarPersonagem(personagemRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<PersonagemResponseDto> editar(@PathVariable Long id,@RequestBody PersonagemRequestDto personagemRequestDto){
        PersonagemResponseDto responseDto = personagemService.editarPersonagem(id, personagemRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable Long id){
        personagemService.deletarPersonagem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    ResponseEntity<List<PersonagemResponseDto>> listar(){
        List<PersonagemResponseDto> responseDto = personagemService.listarPersonagens();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @PutMapping("{id}/adiciona-jutsu")
    ResponseEntity<Boolean> adicionarJutsu(@PathVariable Long id, @RequestBody String jutsu){
        Boolean jutsuAdicionado = personagemService.adiconarJutsu(id, jutsu);
        return new ResponseEntity<>(jutsuAdicionado,HttpStatus.OK);
    }

    @PutMapping("{id}/aumenta-chakra")
    ResponseEntity<Integer> aumentarChakra(@PathVariable Long id, @RequestBody int quantidade){
        int chakraAumentado = personagemService.aumentarChakra(id, quantidade);
        return new ResponseEntity<>(chakraAumentado,HttpStatus.OK);
    }

    @GetMapping("{id}/usa-jutsu")
    ResponseEntity<String> usarJutsu(@PathVariable Long id){
        String jutsuUsado = personagemService.usarJutsu(id);
        return new ResponseEntity<>(jutsuUsado,HttpStatus.OK);
    }

    @GetMapping("{id}/desvia")
    ResponseEntity<String> desviar(@PathVariable Long id){
        String desvio = personagemService.desviar(id);
        return new ResponseEntity<>(desvio,HttpStatus.OK);
    }
}
