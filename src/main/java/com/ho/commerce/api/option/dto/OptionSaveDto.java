package com.ho.commerce.api.option.dto;

import com.ho.commerce.api.option.domain.Option;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionSaveDto {
    private Long optionId;
    private String name;
    private Integer additionalPrice;

    public Option toEntity(){
        return Option.builder()
                .name(this.name)
                .additionalPrice(this.additionalPrice)
                .build();
    }
}
