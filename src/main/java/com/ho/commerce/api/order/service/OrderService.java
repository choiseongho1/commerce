package com.ho.commerce.api.order.service;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.api.option.repository.OptionRepository;
import com.ho.commerce.api.order.domain.Order;
import com.ho.commerce.api.order.dto.OrderSaveDto;
import com.ho.commerce.api.order.repository.OrderRepository;
import com.ho.commerce.api.orderitem.domain.OrderItem;
import com.ho.commerce.api.orderitem.dto.OrderItemSaveDto;
import com.ho.commerce.api.orderitem.repository.OrderItemRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.repository.ProductRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    /* 주문 Repository*/
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    /* 회원 Repository*/
    private final MemberRepository memberRepository;

    /* 상품 Repository*/
    private final ProductRepository productRepository;

    /* 옵션 Repository*/
    private final OptionRepository optionRepository;


    public String createOrderByUser(OrderSaveDto orderSaveDto){

        // MemberId로 판매자정보를 저장한다.
        Optional<Member> opMember = memberRepository.findById(orderSaveDto.getMemberId());
        if(opMember.isEmpty()) throw new CustomException("올바른 사용자 정보가 아닙니다.");


        // OrderSaveDto -> Order Entity
        Order order = orderSaveDto.toEntity();
        order.setMember(opMember.orElseThrow());

        Order savedOrder = orderRepository.save(order);

        for(OrderItemSaveDto orderItemSaveDto : orderSaveDto.getOrderItemSaveList()){
            Optional<Product> opProduct = productRepository.findById(orderItemSaveDto.getProductId());
            if(opProduct.isEmpty()) throw new CustomException("올바른 상품정보가 존재하지 않습니다.");

            Optional<Option> opOption = optionRepository.findById(orderItemSaveDto.getOptionId());
            if(opOption.isEmpty()) throw new CustomException("올바른 옵션 정보가 아닙니다.");

            OrderItem orderItem = orderItemSaveDto.toEntity();

            orderItem.setProduct(opProduct.orElseThrow());
            orderItem.setOption(opOption.orElseThrow());
            orderItem.setOrder(savedOrder);

            orderItemRepository.save(orderItem);

        }
        return savedOrder.getId();


    }
}
