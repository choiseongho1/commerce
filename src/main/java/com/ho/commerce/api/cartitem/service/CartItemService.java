package com.ho.commerce.api.cartitem.service;

import com.ho.commerce.api.cartitem.domain.CartItem;
import com.ho.commerce.api.cartitem.dto.CartItemAddDto;
import com.ho.commerce.api.cartitem.repository.CartItemRepository;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.repository.ProductRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    /**
     * 사용자(User)는 상품(Product)를 장바구니(Cart)에 추가할 수 있다.
     * @param cartItemAddDto
     * @return
     */
    @Transactional
    public Long createCartItemByUser(CartItemAddDto cartItemAddDto){

        // MemberId로 사용자의 정보를 저장한다.
        Optional<Member> opMember = memberRepository.findById(cartItemAddDto.getMemberId());
        if(opMember.isEmpty()) throw new CustomException("올바른 사용자 정보가 아닙니다.");

        // ProductId로 상품의 정보를 저장한다.
        Optional<Product> opProduct = productRepository.findById(cartItemAddDto.getProductId());
        if(opProduct.isEmpty()) throw new CustomException("올바른 상품 정보가 아닙니다.");

        CartItem addCartItem = cartItemAddDto.toEntity();

        addCartItem.setCart(opMember.orElseThrow().getCart());
        addCartItem.setProduct(opProduct.orElseThrow());

        CartItem saveCartItem = cartItemRepository.save(addCartItem);

        return saveCartItem.getCartItemId();
    }

    /**
     * 사용자(User)는 장바구니(Cart)에 담긴 상품(Product)를 삭제한다.
     * @param cartItemId
     */
    @Transactional
    public void deleteCartItemByUser(Long cartItemId){
        // cartItemId로 장바구니 상품 정보를 조회한다.
        Optional<CartItem> opCartItem = cartItemRepository.findById(cartItemId);
        if(opCartItem.isEmpty()) throw new CustomException("올바른 장바구니 상품이 아닙니다.");

        CartItem cartItem = opCartItem.orElseThrow();
        cartItemRepository.delete(cartItem);
    }
}
