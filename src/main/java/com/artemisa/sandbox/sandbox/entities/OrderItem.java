package com.artemisa.sandbox.sandbox.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_item_order"))
    private Order orders;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_item_product"))
    private Product product;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Long unitPrice;

    @Transient
    public Long getTotalPrice() {
        return unitPrice * qty;
    }


}
