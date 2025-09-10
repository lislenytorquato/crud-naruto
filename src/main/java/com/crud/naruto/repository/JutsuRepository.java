package com.crud.naruto.repository;

import com.crud.naruto.model.Jutsu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JutsuRepository extends MongoRepository<Jutsu,Long> {
}
