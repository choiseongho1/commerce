package com.ho.commerce.api.cartitem.repository;

import com.ho.commerce.api.cartitem.dto.CartItemListDto;
import com.ho.commerce.api.cartitem.dto.QCartItemListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ho.commerce.api.cartitem.domain.QCartItem.cartItem;
import static com.ho.commerce.api.option.domain.QOption.option;

@RequiredArgsConstructor
public class CartItemDslRepositoryImpl implements CartItemDslRepository {

    private final JPAQueryFactory queryFactory;


    public List<CartItemListDto> findCartItemListByUser(String memberId){
        return  queryFactory
                .select(
                        new QCartItemListDto(
                                cartItem.cartItemId,
                                cartItem.product.productId,
                                cartItem.product.name,
                                cartItem.quantity,
                                cartItem.product.price,
                                cartItem.product.imgUrl,
                                option.optionId,
                                option.name,
                                option.additionalPrice
                        )
                )
                .from(cartItem)
                .leftJoin(option).on(cartItem.option.eq(option).and(cartItem.product.eq(option.product)))
                .where(
                    cartItem.cart.member.memberId.eq(memberId)
                )
                .fetch();
    }


}
