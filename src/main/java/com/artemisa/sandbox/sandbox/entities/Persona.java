package com.artemisa.sandbox.sandbox.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    @NonNull
    private String nombre;

    @Column(nullable = false)
    @NonNull
    private Integer edad;
}
