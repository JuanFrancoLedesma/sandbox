package com.artemisa.sandbox.sandbox.entities;

import com.artemisa.sandbox.sandbox.repositories.PersonaRepository;
import com.artemisa.sandbox.sandbox.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PersonaRepository personaRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPersonas();
        loadProducts();
    }

    private void loadPersonas(){
        if (personaRepository.count() > 0) return;

        personaRepository.save(new Persona("Juan", 30));
        personaRepository.save(new Persona("Ana", 25));
        personaRepository.save(new Persona("Lucas", 40));
    }

    private void loadProducts(){
        if (productRepository.count() > 0) return;

        productRepository.save(new Product("Teclado", 25000L));
        productRepository.save(new Product("Mouse", 15000L));
        productRepository.save(new Product("Monitor", 180000L));
    }
}

