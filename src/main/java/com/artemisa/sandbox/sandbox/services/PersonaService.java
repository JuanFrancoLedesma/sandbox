package com.artemisa.sandbox.sandbox.services;

import com.artemisa.sandbox.sandbox.ErrorHandlers.exceptions.NoDataException;
import com.artemisa.sandbox.sandbox.entities.Persona;
import com.artemisa.sandbox.sandbox.repositories.PersonaRepository;
import com.artemisa.sandbox.sandbox.shared.PersonaDTO;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository){
        this.personaRepository = personaRepository;
    }

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
//        catch (PropertyValueException e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
//        }
    }

    public Persona buscarPersona(String nombre){
        return personaRepository.findByNombre(nombre).orElse(null);

    }

    public ResponseEntity<PersonaDTO> registerPerson(PersonaDTO personaDTO){
        Persona newPersona = new Persona();
        newPersona.setNombre(personaDTO.getNombre());
        newPersona.setEdad(personaDTO.getEdad());

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

    public Double getAverageAge(){
        List<Persona> personaList = personaRepository.findAll();
        if(personaList.isEmpty()){
            throw new NoDataException("No hay personas en la BD");
        }
        return personaList
                .stream()
                .mapToInt(Persona::getEdad)
                .average()
                .orElseThrow(() -> new NoDataException("Algo fallo al calcular el promedio"));
    }

}
