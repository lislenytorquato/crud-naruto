package com.crud.naruto.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Personagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private Long id;

    private String nome;

    private Map<String,Integer> jutsus;

    private int chakra = 100;

    private int vida;

    public void adicionarJutsu(String jutsu, Integer dano){
        jutsus.put(jutsu, dano);

    }
    public int aumentarChakra(int quantidade){
        return chakra += quantidade;
    }
}
