package com.artemisa.sandbox.sandbox.repositories;

import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
