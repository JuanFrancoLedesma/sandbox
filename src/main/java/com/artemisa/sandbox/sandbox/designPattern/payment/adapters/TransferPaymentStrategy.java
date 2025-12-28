package com.artemisa.sandbox.sandbox.designPattern.payment.adapters;

import com.artemisa.sandbox.sandbox.designPattern.payment.PaymentStrategy;
import com.artemisa.sandbox.sandbox.designPattern.payment.model.PaymentResult;
import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.enums.PaymentResultStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("TRANSFER")
public class TransferPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order) {
        return new PaymentResult(PaymentResultStatus.APPROVED, String.format("TRANSFER-[%s]", UUID.randomUUID()));
    }
}
