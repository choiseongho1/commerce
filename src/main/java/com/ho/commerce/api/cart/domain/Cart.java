package com.ho.commerce.api.cart.domain;

import com.ho.commerce.api.cartitem.domain.CartItem;
import com.ho.commerce.api.member.domain.Member;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

}