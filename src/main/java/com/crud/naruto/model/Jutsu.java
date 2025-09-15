package com.crud.naruto.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "jutsus")
public class Jutsu {

    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    @Id
    private String id;

    @Field(name = "dano")
    private int dano;

    @Field(name = "consumoDeChakra")
    private int consumoDeChakra;

    @Field(name = "nome")
    private String nome;


}
