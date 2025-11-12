package com.artemisa.sandbox.sandbox.services;

import com.artemisa.sandbox.sandbox.entities.Persona;
import com.artemisa.sandbox.sandbox.repositories.PersonaRepository;
import com.artemisa.sandbox.sandbox.shared.PersonaDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    public PersonaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearPersonaMock() {
        //Se prepara el input del test
        PersonaDTO dto = new PersonaDTO();
        dto.setNombre("Franco");

        //Idicamos comportamiento mockeado
        Persona mockPersona = new Persona();
        mockPersona.setId(1L);
        mockPersona.setNombre("Franco");
        when(personaRepository.save(any(Persona.class))).thenReturn(mockPersona);

        //Ejecutamos metodo a probar que se comportara como definimos arriba
        ResponseEntity<PersonaDTO> resultado = personaService.crearPersona(dto);
        PersonaDTO personaResult = resultado.getBody();

        //Validamos resultados
        assertEquals("Franco", personaResult.getNombre());

        //Validamos que solo se haya llamado solo una vez al repositorio
        verify(personaRepository, times(1)).save(any(Persona.class));
    }

    //Se debe crear una persona, debe tener de forma obligada su edad, y su edad y devolver la info con status 201
    //Si ya existe, se debe devolver los datos de la persona con status 200


    @Test
    void registerPersonMock() {
        //Dato de entrada ficticio
        PersonaDTO dto = new PersonaDTO();
        dto.setNombre("Franco");
        dto.setEdad(27);

        //Respuesta de metodo a testear ficticio
        Persona mockPersona = new Persona();
        mockPersona.setId(1L);
        mockPersona.setNombre("Franco");
        mockPersona.setEdad(27);

        when(personaRepository.save(any(Persona.class))).thenReturn(mockPersona);

        //Ejecutamos lo que acabamos de configurar
        ResponseEntity<PersonaDTO> resultado = personaService.crearPersona(dto);
        PersonaDTO personaResult = resultado.getBody();

        //Validamos el resultado segun lo que necesitemos
        assertEquals("Franco", personaResult.getNombre());
        assertEquals(27, personaResult.getEdad());
        verify(personaRepository, times(1)).save(any(Persona.class));
    }

    @Test
    void getAverageEgeMock() {
        List<Persona> personaList = List.of(
                new Persona("Juan", 25),
                new Persona("Franco", 30),
                new Persona("Ledesma", 35)
        );

        //mockeamos la consulta principal del servicio que calcula el promedio
        when(personaRepository.findAll()).thenReturn(personaList);

        //Ejecutamos el servicio a probar
        Double resultado = personaService.getAverageAge();

        assertEquals(30D, resultado, 0.001);
        verify(personaRepository, times(1)).findAll();
    }


}
