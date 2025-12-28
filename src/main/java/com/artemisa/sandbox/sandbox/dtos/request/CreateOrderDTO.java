package com.artemisa.sandbox.sandbox.dtos.request;

import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.entities.Persona;
import com.artemisa.sandbox.sandbox.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateOrderDTO{
    @NotNull(message = "Debe enviar el id del cliente")
    private Long personaId;
    private List<OrderItemDTO> items;

    public Order getEntity(Persona persona){
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setPersona(persona);
        order.setStatus(OrderStatus.CREATED);
        return order;
    }
}
