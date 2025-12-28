package com.artemisa.sandbox.sandbox.controllers;

import com.artemisa.sandbox.sandbox.dtos.request.PayOrderDTO;
import com.artemisa.sandbox.sandbox.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}/pay")
    public void pay(
            @PathVariable("orderId") Long orderId,
            @RequestBody PayOrderDTO payOrderDTO){
        paymentService.payOrder(orderId, payOrderDTO);
    }
}
