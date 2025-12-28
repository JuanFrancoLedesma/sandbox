package com.artemisa.sandbox.sandbox.dtos.request;

import com.artemisa.sandbox.sandbox.enums.PaymentMethod;
import lombok.Getter;

@Getter
public class PayOrderDTO {
    private PaymentMethod method;
}
