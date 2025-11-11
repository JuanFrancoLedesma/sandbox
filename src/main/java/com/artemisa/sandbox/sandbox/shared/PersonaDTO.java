package com.artemisa.sandbox.sandbox.shared;

import com.artemisa.sandbox.sandbox.entities.Persona;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
    private Long id;
    private String nombre;

    public void generateDTO(Persona persona){
        this.id = persona.getId();
        this.nombre = persona.getNombre();
    }
}
