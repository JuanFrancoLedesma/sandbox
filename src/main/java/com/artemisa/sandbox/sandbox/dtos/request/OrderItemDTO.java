package com.artemisa.sandbox.sandbox.dtos.request;

import com.artemisa.sandbox.sandbox.entities.Order;
import com.artemisa.sandbox.sandbox.entities.OrderItem;
import com.artemisa.sandbox.sandbox.entities.Product;
import lombok.Getter;

@Getter
public class OrderItemDTO{
    private Integer qty;
    private Long productId;

    public OrderItem getEntity(Order order, Product product){
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(order);
        orderItem.setQty(this.qty);
        orderItem.setProduct(product);
        orderItem.setUnitPrice(product.getPrice());

        return orderItem;
    }
}
