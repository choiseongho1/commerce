package com.ho.commerce.api.cartitem.controller;

import com.ho.commerce.api.cartitem.dto.CartItemAddDto;
import com.ho.commerce.api.cartitem.dto.CartItemListDto;
import com.ho.commerce.api.cartitem.service.CartItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/api/v1/cart-item")
@RequiredArgsConstructor
public class UserCartItemController {

    private final CartItemService cartItemService;

    /**
     * 사용자(User)는 상품(Product)를 장바구니(Cart)에 추가할 수 있다.
     * @param cartItemAddDto
     * @return Long cartItemId
     */
    @PostMapping
    public ResponseEntity<Object> createCartItemByUser(@RequestBody CartItemAddDto cartItemAddDto, HttpServletRequest request){
        cartItemAddDto.setMemberIdFromRequest(request);

        Long cartItemId = cartItemService.createCartItemByUser(cartItemAddDto);
        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
    }

    /**
     * 사용자(User)는 상품(Product)를 장바구니(Cart)에 추가할 수 있다.
     * @param cartItemAddDto
     * @return Long cartItemId
     */
    @GetMapping
    public ResponseEntity<Object> findCartItemListByUser(HttpServletRequest request){
        String memberId = request.getHeader("memberId");

        List<CartItemListDto> cartItemList = cartItemService.findCartItemListByUser(memberId);
        return new ResponseEntity<>(cartItemList, HttpStatus.OK);
    }

    /**
     * 사용자(User)는 상품(Product)를 장바구니(Cart)에 삭제할 수 있다.
     * @param cartItemAddDto
     * @return Long cartItemId
     */
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Object> deleteCartItemByUser(@PathVariable Long cartItemId){

        cartItemService.deleteCartItemByUser(cartItemId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
