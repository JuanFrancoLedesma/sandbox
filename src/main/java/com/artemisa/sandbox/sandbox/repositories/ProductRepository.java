package com.artemisa.sandbox.sandbox.repositories;

import com.artemisa.sandbox.sandbox.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
