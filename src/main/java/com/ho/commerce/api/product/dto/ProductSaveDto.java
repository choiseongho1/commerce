package com.ho.commerce.api.product.dto;

import com.ho.commerce.api.product.domain.Product;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveDto {

    private Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private Long categoryId;
    private String memberId;

    public Product toEntity(){
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .build();
    }

    public void toEntity(Product product){
        if( product == null ) return;

        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setStockQuantity(this.price);
    }

}
