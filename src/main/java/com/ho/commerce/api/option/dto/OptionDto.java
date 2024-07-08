package com.ho.commerce.api.option.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class OptionDto {
    private Long optionId;
    private String name;
    private Integer additionalPrice;

    @QueryProjection
    public OptionDto(Long optionId, String name, Integer additionalPrice){
        this.optionId = optionId;
        this.name = name;
        this.additionalPrice = additionalPrice;
    }
}
