package com.ho.commerce.api.wishlistitem.domain;

import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.wishlist.domain.WishList;
import jakarta.persistence.*;

@Entity
public class WishListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistItemId;

    @ManyToOne
    @JoinColumn(name = "wishlistId")
    private WishList wishlist;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}