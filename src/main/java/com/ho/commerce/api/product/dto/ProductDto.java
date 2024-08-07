package com.ho.commerce.api.product.dto;

import com.ho.commerce.api.option.dto.OptionDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private Long categoryId;
    private String imgUrl;


    private List<OptionDto> options = new ArrayList<>();
    @QueryProjection
    public ProductDto(Long productId, String name, String description, Integer price, Integer stockQuantity, Long categoryId, String imgUrl){
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.imgUrl = imgUrl;
    }

    @QueryProjection
    public ProductDto(Long productId, String name, String description, Integer price, Integer stockQuantity, Long categoryId, String imgUrl, List<OptionDto> options){
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.imgUrl = imgUrl;
        this.options = options;
    }

}
