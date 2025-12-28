package com.artemisa.sandbox.sandbox.dtos;

import com.artemisa.sandbox.sandbox.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrdersDTO {
    private LocalDateTime createdAt;
    private Long totalAmount;
    private OrderStatus status;
    private String personaName;
}
