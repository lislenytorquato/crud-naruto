package com.crud.naruto.repository;

import com.crud.naruto.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem,Long> {
}
