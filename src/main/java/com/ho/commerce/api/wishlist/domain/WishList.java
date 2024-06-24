package com.ho.commerce.api.wishlist.domain;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.wishlistitem.domain.WishListItem;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "wishlist")
    private List<WishListItem> wishlistItems;

    // Getters and Setters
    // Constructor
}