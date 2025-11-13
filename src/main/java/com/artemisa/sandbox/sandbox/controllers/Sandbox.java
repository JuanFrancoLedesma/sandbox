package com.artemisa.sandbox.sandbox.controllers;

import com.artemisa.sandbox.sandbox.entities.Persona;
import com.artemisa.sandbox.sandbox.services.PersonaService;
import com.artemisa.sandbox.sandbox.shared.PersonaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sandbox")
public class Sandbox {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Hola desde el sandbox!";
    }

    @GetMapping("/hello/a/{nombre}")
    public String sayHelloToSomeone(@PathVariable String nombre){
        return "Hola " + nombre + ", saludos desde el sandbox!";
    }

    @GetMapping("/hello/a/{nombre}/greeting")
    public String sayHelloToSomeoneAndFareWell(@PathVariable String nombre, @RequestParam(defaultValue = "1") int feeling){
        return feeling > 0 ? "Hola " + nombre + ", que te vaya bien!" : "Hola " + nombre + ", tomatela de aca!";
    }

    @PostMapping("/create/persona")
    public ResponseEntity<PersonaDTO> createPersona(@Valid @RequestBody PersonaDTO persona){
        return personaService.crearPersona(persona);
    }

    @PostMapping("/register/persona")
    public ResponseEntity<PersonaDTO> registerPersona(@Valid @RequestBody PersonaDTO persona){
        return personaService.registerPerson(persona);
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> getAverageAge(){
        Double result = personaService.getAverageAge();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/buscar/{name}")
    public ResponseEntity<PersonaDTO> getPersonaByNombre(@PathVariable String name){
        PersonaDTO result = personaService.getPersonaByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}