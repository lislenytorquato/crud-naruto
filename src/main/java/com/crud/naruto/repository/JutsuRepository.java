package com.crud.naruto.repository;

import com.crud.naruto.model.Jutsu;
import com.crud.naruto.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JutsuRepository extends JpaRepository<Jutsu,Long> {
}
