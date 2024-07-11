package com.ho.commerce.api.cartitem.dto;

import com.ho.commerce.common.dto.BaseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class CartItemListDto extends BaseDto {


    private Long cartItemId;
    private Long productId;
    private String name;
    private Integer quantity;
    private Integer price;
    private String imgUrl;

    private Long optionId;
    private String optionName;
    private Integer additionalPrice;

    @QueryProjection
    public CartItemListDto(Long cartItemId, Long productId, String name, Integer quantity, Integer price, String imgUrl, Long optionId, String optionName, Integer additionalPrice){
        this.cartItemId = cartItemId;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
        this.optionId = optionId;
        this.optionName = optionName;
        this.additionalPrice = additionalPrice;
    }
}
