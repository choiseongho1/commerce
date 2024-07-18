package com.ho.commerce.api.order.service;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.api.order.domain.Order;
import com.ho.commerce.api.order.dto.OrderSaveDto;
import com.ho.commerce.api.order.repository.OrderRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    /* 주문 Repository*/
    private OrderRepository orderRepository;

    /* 회원 Repository*/
    private MemberRepository memberRepository;


    public String createOrderByUser(OrderSaveDto orderSaveDto){

        // MemberId로 판매자정보를 저장한다.
        Optional<Member> opMember = memberRepository.findById(orderSaveDto.getMemberId());
        if(opMember.isEmpty()) throw new CustomException("올바른 사용자 정보가 아닙니다.");


        // OrderSaveDto -> Order Entity
        Order order = orderSaveDto.toEntity();
        order.setMember(opMember.orElseThrow());

        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();


    }
}
