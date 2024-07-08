package com.ho.commerce.api.cartitem.dto;

import com.ho.commerce.api.cartitem.domain.CartItem;
import com.ho.commerce.common.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemAddDto extends BaseDto {

    private Long productId;
    private Integer quantity;

    public CartItem toEntity(){
        return CartItem.builder()
                .quantity(this.quantity)
                .build();
    }
}
