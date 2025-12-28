package com.artemisa.sandbox.sandbox.entities;

import com.artemisa.sandbox.sandbox.enums.OrderStatus;
import com.artemisa.sandbox.sandbox.enums.PaymentMethod;
import com.artemisa.sandbox.sandbox.enums.PaymentResultStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_order"))
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentResultStatus status;

    @Column(nullable = false)
    private Long amount;

    @Column(length = 100)
    private String externalRef;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
