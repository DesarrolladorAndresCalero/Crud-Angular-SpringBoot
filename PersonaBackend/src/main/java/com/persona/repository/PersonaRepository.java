package com.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persona.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

}
