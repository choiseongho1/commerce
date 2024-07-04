package com.ho.commerce.api.product.dto;

import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.common.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaveDto extends BaseDto {

    private Long productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private String imgUrl;

    private Long categoryId;

    public Product toEntity(){
        return Product.builder()
                .name(this.name)
                .description(this.description)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .imgUrl(this.imgUrl)
                .build();
    }

    public void toEntity(Product product){
        if( product == null ) return;

        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setStockQuantity(this.stockQuantity);
        product.setImgUrl(this.imgUrl);
    }

}
