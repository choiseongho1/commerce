package com.ho.commerce.api.option.dto;

import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.common.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionSaveDto extends BaseDto {
    private Long optionId;
    private String name;
    private Integer additionalPrice;

    public Option toEntity(){
        return Option.builder()
                .name(this.name)
                .additionalPrice(this.additionalPrice)
                .build();
    }

    public void toEntity(Option option){
        if( option == null ) return;

        option.setName(this.name);
        option.setAdditionalPrice(this.additionalPrice);
    }
}
