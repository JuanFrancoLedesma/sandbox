package com.artemisa.sandbox.sandbox.designPattern.payment.adapters;

import com.artemisa.sandbox.sandbox.designPattern.payment.PaymentStrategy;
import com.artemisa.sandbox.sandbox.designPattern.payment.model.PaymentResult;
import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.enums.PaymentResultStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("WALLET")
public class WalletPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order) {
        boolean approved = order.getTotalAmount() < 200_000;

        return new PaymentResult(approved ? PaymentResultStatus.APPROVED : PaymentResultStatus.REJECTED, String.format("WALLET-[%s]", UUID.randomUUID()));
    }
}
