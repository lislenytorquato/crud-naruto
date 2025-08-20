package com.crud.naruto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private int idade;

    @Getter
    @Setter
    private String aldeia;

    @Getter
    @Setter
    private List<String> jutsus;

    @Getter
    @Setter
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
