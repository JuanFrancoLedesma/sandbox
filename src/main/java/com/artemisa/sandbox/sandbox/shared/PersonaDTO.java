package com.artemisa.sandbox.sandbox.shared;

import com.artemisa.sandbox.sandbox.entities.Persona;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
    private Long id;

    @NotBlank(message = "Debe enviar nombre")
    private String nombre;

    @NotNull(message = "Debe enviar la edad")
    private Integer edad;

    public void generateDTO(Persona persona){
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.edad = persona.getEdad();
    }
}
