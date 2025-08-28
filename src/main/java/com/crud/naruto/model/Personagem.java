package com.crud.naruto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Long id;

    private String nome;

    @ManyToMany
    @MapKey(name = "nome")
    private Map<String,Jutsu> jutsus;

    private int chakra = 100;

    private int vida;

    public void adicionarJutsu(String nome, Jutsu jutsu){
        jutsus.put(nome, jutsu);

    }
    public int aumentarChakra(int quantidade){
        return chakra += quantidade;
    }
}
