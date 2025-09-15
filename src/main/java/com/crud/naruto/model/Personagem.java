package com.crud.naruto.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "personagens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Personagem {

    @Id
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private String id;

    private String nome;

   private List<Jutsu> jutsus;

    private int chakra = 100;

    private int vida;

    public void adicionarJutsu(Jutsu jutsu){
        jutsus.add(jutsu);

    }
    public int aumentarChakra(int quantidade){
        return chakra += quantidade;
    }
}
