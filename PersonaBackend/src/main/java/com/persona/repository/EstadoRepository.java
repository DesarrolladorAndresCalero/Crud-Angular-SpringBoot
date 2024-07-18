package com.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persona.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
