package com.ho.commerce.api.order.dto;

import com.ho.commerce.api.order.domain.Order;
import com.ho.commerce.api.orderitem.dto.OrderItemSaveDto;
import com.ho.commerce.common.dto.BaseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaveDto extends BaseDto {

    private String orderId;
    private String address;
    private String addressDetail;
    private String orderDate;
    private String orderStatus;

    private List<OrderItemSaveDto> orderItemSaveList = new ArrayList<>();

    public Order toEntity(){
        return Order.builder()
                .orderId(this.orderId)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .orderDate(LocalDateTime.now())
                .status("ordered")
                .build();
    }
}
