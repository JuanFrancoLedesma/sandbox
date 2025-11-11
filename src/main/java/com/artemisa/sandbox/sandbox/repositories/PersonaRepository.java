package com.artemisa.sandbox.sandbox.repositories;

import com.artemisa.sandbox.sandbox.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByNombre(String nombre);
}
