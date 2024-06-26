package com.ho.commerce.api.product.controller;

import com.ho.commerce.api.product.dto.ProductCondDto;
import com.ho.commerce.api.product.dto.ProductListDto;
import com.ho.commerce.api.product.dto.ProductSaveDto;
import com.ho.commerce.api.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/api/v1/product")
@RequiredArgsConstructor
public class UserProductController {

    private final ProductService productService;

    /**
     * 판매자(Seller)가 등록한 상품(Product)목록을 조회한다.
     * @param productSaveDto
     * @return Long ProductId
     */
    @GetMapping
    public ResponseEntity<Object> findProductListBySeller(ProductCondDto productCondDto){
        List<ProductListDto> productList = productService.findProductListBySeller(productCondDto);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

}
