package com.artemisa.sandbox.sandbox.services;

import com.artemisa.sandbox.sandbox.dtos.ProductDTO;
import com.artemisa.sandbox.sandbox.entities.Product;
import com.artemisa.sandbox.sandbox.repositories.PersonaRepository;
import com.artemisa.sandbox.sandbox.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findProducts() {
        return productRepository.findAll().stream().map(p ->
                ProductDTO.builder()
                        .price(p.getPrice())
                        .name(p.getName())
                        .build()
        ).toList();
    }

}
