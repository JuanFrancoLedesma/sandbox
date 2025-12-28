package com.artemisa.sandbox.sandbox.designPattern.payment;

import com.artemisa.sandbox.sandbox.designPattern.payment.model.PaymentResult;
import com.artemisa.sandbox.sandbox.entities.Order;

public interface PaymentStrategy {
    PaymentResult pay(Order order);
}
