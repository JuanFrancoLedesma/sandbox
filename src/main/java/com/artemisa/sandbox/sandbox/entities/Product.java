package com.artemisa.sandbox.sandbox.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private Long price;
}
