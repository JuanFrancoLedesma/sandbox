package com.artemisa.sandbox.sandbox.controllers;

import com.artemisa.sandbox.sandbox.dtos.OrdersDTO;
import com.artemisa.sandbox.sandbox.dtos.ProductDTO;
import com.artemisa.sandbox.sandbox.dtos.request.CreateOrderDTO;
import com.artemisa.sandbox.sandbox.dtos.request.OrderItemDTO;
import com.artemisa.sandbox.sandbox.services.OrdersService;
import com.artemisa.sandbox.sandbox.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/products")
    private List<ProductDTO> getProducts(){
        return productService.findProducts();
    }

    @GetMapping("")
    private List<OrdersDTO> getOrders(){
        return ordersService.findOrders();
    }

    @PostMapping("")
    private void createOrder(@RequestBody CreateOrderDTO createOrderDTO){
        ordersService.createOrderWithItems(createOrderDTO);
    }

    @PutMapping("/add/{orderId}")
    private void addItem(@PathVariable("orderId") Long orderId, @RequestBody List<OrderItemDTO> orderItemDTOList) throws Exception {
        ordersService.addItem(orderId, orderItemDTOList);
    }

    @PutMapping("/close/{orderId}")
    private void close(@PathVariable("orderId") Long orderId) throws Exception {
        ordersService.closeOrder(orderId);
    }


}
