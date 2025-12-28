package com.artemisa.sandbox.sandbox.designPattern.payment.model;

import com.artemisa.sandbox.sandbox.enums.PaymentResultStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResult {
    private PaymentResultStatus status;
    private String externalRef;
}
