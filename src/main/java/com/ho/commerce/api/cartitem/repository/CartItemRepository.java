package com.ho.commerce.api.cartitem.repository;

import com.ho.commerce.api.cartitem.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>, CartItemDslRepository{
}
