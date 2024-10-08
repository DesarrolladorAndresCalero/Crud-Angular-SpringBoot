package com.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persona.model.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer>{

}
