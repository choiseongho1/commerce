package com.ho.commerce.api.category.dto;

import com.ho.commerce.api.category.domain.Category;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CategorySaveDto {

    private Long categoryId;

    private String name;

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .build();
    }
}
