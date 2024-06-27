package com.ho.commerce.api.category.dto;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.product.domain.Product;
import lombok.*;

import static com.ho.commerce.api.product.domain.QProduct.product;

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

    public void toEntity(Category category){
        if( category == null ) return;
        category.setName(this.name);
    }
}
