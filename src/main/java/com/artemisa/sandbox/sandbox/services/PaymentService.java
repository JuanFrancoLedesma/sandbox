package com.artemisa.sandbox.sandbox.services;

import com.artemisa.sandbox.sandbox.ErrorHandlers.exceptions.NoDataException;
import com.artemisa.sandbox.sandbox.designPattern.payment.PaymentStrategy;
import com.artemisa.sandbox.sandbox.designPattern.payment.PaymentStrategyFactory;
import com.artemisa.sandbox.sandbox.designPattern.payment.model.PaymentResult;
import com.artemisa.sandbox.sandbox.dtos.request.PayOrderDTO;
import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.entities.Payment;
import com.artemisa.sandbox.sandbox.enums.OrderStatus;
import com.artemisa.sandbox.sandbox.enums.PaymentMethod;
import com.artemisa.sandbox.sandbox.enums.PaymentResultStatus;
import com.artemisa.sandbox.sandbox.repositories.OrdersRepository;
import com.artemisa.sandbox.sandbox.repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final OrdersRepository ordersRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyFactory paymentStrategyFactory;

    public PaymentService(OrdersRepository ordersRepository, PaymentRepository paymentRepository, PaymentStrategyFactory paymentStrategyFactory) {
        this.ordersRepository = ordersRepository;
        this.paymentRepository = paymentRepository;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    @Transactional
    public void payOrder(Long orderId, PayOrderDTO payOrderDTO){
        Order order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new NoDataException(
                        String.format("No se encontro la orden con id [%s]", orderId))
                );

        if(!order.getStatus().equals(OrderStatus.PAYMENT_PENDING)) {
            throw new IllegalStateException(
                    String.format("La orden [%s] no esta disponible para pagar - [%s]", orderId, order.getStatus())
            );
        }

        PaymentStrategy strategy = paymentStrategyFactory.getStrategy(payOrderDTO.getMethod());

        PaymentResult result = strategy.pay(order);

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setMethod(payOrderDTO.getMethod());
        payment.setStatus(result.getStatus());
        payment.setExternalRef(result.getExternalRef());
        payment.setAmount(order.getTotalAmount());
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        updateOrderStatus(order, result.getStatus());

    }

    private void updateOrderStatus(Order order, PaymentResultStatus status) {
        if (status == PaymentResultStatus.APPROVED) {
            order.setStatus(OrderStatus.PAID);
        }
    }

}
