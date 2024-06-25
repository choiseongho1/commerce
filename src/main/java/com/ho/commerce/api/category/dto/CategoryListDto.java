package com.ho.commerce.api.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class CategoryListDto {

    private Long categoryId;

    private String name;

    @QueryProjection
    public CategoryListDto(Long categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }
}
