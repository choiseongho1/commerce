package com.ho.commerce.api.product.dto;

import com.ho.commerce.common.dto.BaseDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProductListDto extends BaseDto {

    private Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private Long categoryId;
    private String imgUrl;

    @QueryProjection
    public ProductListDto(Long productId, String name, String description, Integer price, Integer stockQuantity, Long categoryId, String imgUrl){
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.imgUrl = imgUrl;
    }

}
