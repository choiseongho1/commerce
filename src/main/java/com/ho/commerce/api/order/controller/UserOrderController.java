package com.ho.commerce.api.order.controller;

import com.ho.commerce.api.order.dto.OrderSaveDto;
import com.ho.commerce.api.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/api/v1/order")
@RequiredArgsConstructor
public class UserOrderController {

    private final OrderService orderService;

    /**
     * 사용자(User)는 상품(Product)를 주문(Order)한다.
     * @param OrderSaveDto
     * @return String orderId
     */
    @PostMapping
    public ResponseEntity<Object> createOrderByUser(@RequestBody OrderSaveDto orderSaveDto, HttpServletRequest request){
        orderSaveDto.setMemberIdFromRequest(request);

        String orderId = orderService.createOrderByUser(orderSaveDto);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
