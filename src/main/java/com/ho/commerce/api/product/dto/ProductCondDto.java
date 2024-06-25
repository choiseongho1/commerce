package com.ho.commerce.api.product.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductCondDto {

    private String name;
    private Integer price;
    private Long categoryId;
    private String memberId;

}
