package com.ho.commerce.api.cartitem.domain;

import com.ho.commerce.api.cart.domain.Cart;
import com.ho.commerce.api.product.domain.Product;
import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private int quantity;
}