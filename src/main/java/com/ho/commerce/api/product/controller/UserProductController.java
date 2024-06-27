package com.ho.commerce.api.product.controller;

import com.ho.commerce.api.product.dto.ProductCondDto;
import com.ho.commerce.api.product.dto.ProductListDto;
import com.ho.commerce.api.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/api/v1/product")
@RequiredArgsConstructor
public class UserProductController {

    private final ProductService productService;

    /**
     * 사용자(USER)는 등록된 상품(Product)목록을 조회한다.
     * @param ProductCondDto productCondDto
     * @return List<ProductListDto>
     */
    @GetMapping
    public ResponseEntity<Object> findProductListByUser(ProductCondDto productCondDto){
        List<ProductListDto> productList = productService.findProductListByUser(productCondDto);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
