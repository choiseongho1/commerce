package com.ho.commerce.api.orderitem.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemSaveDto {
    private Long orderItemId;
    private Integer quantity;
    private Integer unitPrice;
}
