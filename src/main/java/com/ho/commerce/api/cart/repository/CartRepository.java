package com.ho.commerce.api.cart.repository;

import com.ho.commerce.api.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
}
