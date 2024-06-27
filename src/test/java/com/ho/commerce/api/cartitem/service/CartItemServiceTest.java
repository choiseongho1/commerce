package com.ho.commerce.api.cartitem.service;

import com.ho.commerce.api.cartitem.domain.CartItem;
import com.ho.commerce.api.cartitem.dto.CartItemAddDto;
import com.ho.commerce.api.cartitem.repository.CartItemRepository;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Rollback(false)
public class CartItemServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartItemService;

    @Test
    @DisplayName("유저가 올바른 상품을 카트에 추가하는 경우")
    public void testCreateCartItemByUser_ValidProduct() {
        // given
        Member member = Member.builder()
                .memberId("memberId")
                .name("A")
                .build();
        Member saveMember = memberRepository.save(member);

        Product product = Product.builder()
                .name("Product A")
                .build();
        Product saveProduct = productRepository.save(product);

        CartItemAddDto cartItemAddDto = new CartItemAddDto();
        cartItemAddDto.setMemberId(saveMember.getMemberId());
        cartItemAddDto.setProductId(saveProduct.getProductId());

        // when
        Long cartItemId = cartItemService.createCartItemByUser(cartItemAddDto);

        // then
        Assertions.assertNotNull(cartItemId);

        // 생성된 CartItem을 조회하여 확인할 수도 있음
        CartItem savedCartItem = cartItemRepository.findById(cartItemId).orElse(null);
        Assertions.assertNotNull(savedCartItem);
        Assertions.assertEquals(savedCartItem.getProduct().getProductId(), saveProduct.getProductId());
    }



}