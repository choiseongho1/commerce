package com.ho.commerce.api.orderitem.dto;

import com.ho.commerce.api.orderitem.domain.OrderItem;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemSaveDto {
    private Long orderItemId;
    private Long productId;
    private Long optionId;
    private Integer additionalPrice;
    private Integer price;
    private Integer quantity;

    public OrderItem toEntity(){
        return OrderItem.builder()
                .totalPrice(this.additionalPrice+this.price)
                .quantity(this.quantity)
                .build();
    }
}
