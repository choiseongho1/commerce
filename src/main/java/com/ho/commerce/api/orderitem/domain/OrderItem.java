package com.ho.commerce.api.orderitem.domain;

import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.api.order.domain.Order;
import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orderItem")
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

    @ManyToOne
    @JoinColumn(name = "optionId")
    private Option option;

    private Integer totalPrice;
    private Integer quantity;

}