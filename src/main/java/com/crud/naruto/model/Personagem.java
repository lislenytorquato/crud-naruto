package com.crud.naruto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Personagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) private Long id;

    private String nome;

    private int idade;

    private String aldeia;

    private List<String> jutsus;

    private int chakra;

    public boolean adicionarJutsu(String novoJutsu){
        return jutsus.add(novoJutsu);

    }
    public int aumentarChakra(int quantidade){
        return chakra += quantidade;
    }


    @Override
    public String toString() {

        return "{"+ "nome: " + nome + " idade: " + idade + " aldeia: " + aldeia + " jutsus: "  + jutsus.stream().sorted().toList() + " chakra: " + chakra + "}";
    }
}
