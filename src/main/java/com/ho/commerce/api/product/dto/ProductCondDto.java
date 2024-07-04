package com.ho.commerce.api.product.dto;

import com.ho.commerce.common.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductCondDto extends BaseDto {

    private String name;
    private Integer price;
    private Long categoryId;

}
