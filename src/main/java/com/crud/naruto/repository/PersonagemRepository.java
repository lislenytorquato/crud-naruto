package com.crud.naruto.repository;

import com.crud.naruto.model.Personagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonagemRepository extends MongoRepository<Personagem,Long> {
}
