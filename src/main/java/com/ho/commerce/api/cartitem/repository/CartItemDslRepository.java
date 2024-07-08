package com.ho.commerce.api.cartitem.repository;

import com.ho.commerce.api.cartitem.dto.CartItemListDto;

import java.util.List;

public interface CartItemDslRepository {

    List<CartItemListDto> findCartItemListByUser(String memberId);
}
