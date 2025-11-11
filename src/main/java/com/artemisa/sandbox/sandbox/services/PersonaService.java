package com.artemisa.sandbox.sandbox.services;

import com.artemisa.sandbox.sandbox.entities.Persona;
import com.artemisa.sandbox.sandbox.repositories.PersonaRepository;
import com.artemisa.sandbox.sandbox.shared.PersonaDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

//    public PersonaDTO crearPersona(PersonaDTO personaDTO){
//        Persona checkPersona = buscarPersona(personaDTO.getNombre());
//        if(checkPersona != null){
//            System.out.println("Ya existe la persona");
//            PersonaDTO checkPersonaDTO = new PersonaDTO();
//            checkPersonaDTO.generateDTO(checkPersona);
//            return checkPersonaDTO;
//        }
//
//        Persona newPersona = new Persona();
//        newPersona.setNombre(personaDTO.getNombre());
//        Persona persona = personaRepository.save(newPersona);
//
//        PersonaDTO newPersonaDTO = new PersonaDTO();
//        newPersonaDTO.generateDTO(persona);
//        return newPersonaDTO;
//    }

    public ResponseEntity<PersonaDTO> crearPersona(PersonaDTO personaDTO){
        Persona newPersona = new Persona();
        newPersona.setNombre(personaDTO.getNombre());

        try {
            Persona persona = personaRepository.save(newPersona);
            PersonaDTO newPersonaDTO = new PersonaDTO();
            newPersonaDTO.generateDTO(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPersonaDTO);
        } catch (DataIntegrityViolationException e){
            // Ya existia una persona con ese nombre
            Persona checkPersona = buscarPersona(personaDTO.getNombre());
            PersonaDTO checkPersonaDTO = new PersonaDTO();
            checkPersonaDTO.generateDTO(checkPersona);
            return ResponseEntity.status(HttpStatus.OK).body(checkPersonaDTO);
        }
    }

    public Persona buscarPersona(String nombre){
        return personaRepository.findByNombre(nombre).orElse(null);

    }

}
