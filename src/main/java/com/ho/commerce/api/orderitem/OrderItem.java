package com.ho.commerce.api.orderitem;

import com.ho.commerce.api.order.domain.Order;
import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private int quantity;
    private BigDecimal unitPrice;

}