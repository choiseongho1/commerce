package com.ho.commerce.api.order.domain;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.orderitem.OrderItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private LocalDateTime orderDate;
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Embedded
    private Address address;

}