package com.artemisa.sandbox.sandbox.services;

import com.artemisa.sandbox.sandbox.ErrorHandlers.exceptions.NoDataException;
import com.artemisa.sandbox.sandbox.dtos.OrdersDTO;
import com.artemisa.sandbox.sandbox.dtos.ProductDTO;
import com.artemisa.sandbox.sandbox.dtos.request.CreateOrderDTO;
import com.artemisa.sandbox.sandbox.dtos.request.OrderItemDTO;
import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.entities.OrderItem;
import com.artemisa.sandbox.sandbox.entities.Persona;
import com.artemisa.sandbox.sandbox.entities.Product;
import com.artemisa.sandbox.sandbox.enums.OrderStatus;
import com.artemisa.sandbox.sandbox.repositories.OrderItemRepository;
import com.artemisa.sandbox.sandbox.repositories.OrdersRepository;
import com.artemisa.sandbox.sandbox.repositories.PersonaRepository;
import com.artemisa.sandbox.sandbox.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private OrdersRepository ordersRepository;
    private PersonaRepository personaRepository;
    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;

    public OrdersService(
            OrdersRepository ordersRepository,
            PersonaRepository personaRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository
    ) {
        this.ordersRepository = ordersRepository;
        this.personaRepository = personaRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrdersDTO> findOrders() {
        return ordersRepository.findAll().stream().map(o ->
                OrdersDTO.builder()
                        .totalAmount(o.getTotalAmount())
                        .createdAt(o.getCreatedAt())
                        .personaName(o.getPersona().getNombre())
                        .status(o.getStatus())
                        .build()
        ).toList();
    }

    @Transactional
    public void createOrderWithItems(CreateOrderDTO createOrderDTO){
        Order newOrder = createOrder(createOrderDTO);

        Long totalAmount = createOrderDTO.getItems().stream()
                .mapToLong(oiDTO -> createItem(newOrder, oiDTO))
                .sum();

        newOrder.setTotalAmount(totalAmount);
    }

    private Order createOrder(CreateOrderDTO createOrderDTO){
        Persona persona = personaRepository.findById(createOrderDTO.getPersonaId())
                .orElseThrow(() -> new NoDataException("No se encontró cliente en BD"));

        Order newOrder = createOrderDTO.getEntity(persona);
        return ordersRepository.save(newOrder);
    }

    private Long createItem(Order newOrder, OrderItemDTO orderItemDTO){
        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() ->
                        new NoDataException("No se encontro producto con id " + orderItemDTO.getProductId()
                        )
                );
        OrderItem orderItem = orderItemRepository.save(orderItemDTO.getEntity(newOrder, product));
        return orderItem.getTotalPrice();
    }

    @Transactional
    public void addItem(Long orderId, List<OrderItemDTO> orderItemDTOList) throws Exception {
        Order order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new NoDataException("No se encontró orden en BD"));
        if(!order.getStatus().equals(OrderStatus.CREATED)){
            throw new Exception("La orden no admite mas items");
        }
        Long extraAmount = orderItemDTOList.stream().mapToLong(item -> createItem(order, item)).sum();

        order.setTotalAmount(order.getTotalAmount() + extraAmount);
    }

    @Transactional
    public void closeOrder(Long orderId){
        Order order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new NoDataException("No se encontró orden en BD"));

        if (order.getStatus() != OrderStatus.CREATED) {
            throw new IllegalStateException("La orden no se puede cerrar en este estado");
        }

        order.setStatus(OrderStatus.PAYMENT_PENDING);
    }

}



