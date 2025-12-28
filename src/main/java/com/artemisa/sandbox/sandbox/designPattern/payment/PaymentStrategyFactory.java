package com.artemisa.sandbox.sandbox.designPattern.payment;

import com.artemisa.sandbox.sandbox.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentStrategyFactory {

    private final Map<PaymentMethod, PaymentStrategy> strategies;

    public PaymentStrategy getStrategy(PaymentMethod method){
        PaymentStrategy strategy = strategies.get(method);

        if(strategy == null) {
            throw new IllegalArgumentException("Metodo de pago no soportado");
        }

        return strategy;
    }
}
