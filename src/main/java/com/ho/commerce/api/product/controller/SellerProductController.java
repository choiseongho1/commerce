package com.ho.commerce.api.product.controller;

import com.ho.commerce.api.product.dto.ProductCondDto;
import com.ho.commerce.api.product.dto.ProductDto;
import com.ho.commerce.api.product.dto.ProductListDto;
import com.ho.commerce.api.product.dto.ProductSaveDto;
import com.ho.commerce.api.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/seller/api/v1/product")
@RequiredArgsConstructor
public class SellerProductController {

    private final ProductService productService;

    /**
     * 판매자(Seller)가 상품(Product)를 등록한다.
     * @param productSaveDto
     * @return Long ProductId
     */
    @PostMapping
    public ResponseEntity<Long> createProductBySeller(@RequestBody ProductSaveDto productSaveDto, HttpServletRequest request){

        productSaveDto.setMemberIdFromRequest(request);

        Long productId = productService.createProductBySeller(productSaveDto);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    /**
     * 판매자(Seller)가 상품(Product)를 수정한다.
     * @param productSaveDto
     * @return Long ProductId
     */
    @PutMapping
    public ResponseEntity<Long> updateProductBySeller(@RequestBody ProductSaveDto productSaveDto){

        Long productId = productService.updateProductBySeller(productSaveDto);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    /**
     * 판매자(Seller)가 등록한 상품(Product)목록을 조회한다.
     * @param productSaveDto
     * @return Long ProductId
     */
    @GetMapping
    public ResponseEntity<Object> findProductListBySeller(ProductCondDto productCondDto, HttpServletRequest request){

        productCondDto.setMemberIdFromRequest(request);

        List<ProductListDto> productList = productService.findProductListBySeller(productCondDto);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * 판매자(Seller)가 등록한 상품(Product)을 상세 조회한다.
     * @param Long productId
     * @return ProductDto productInfo
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Object> findProductInfoBySeller(@PathVariable Long productId){
        ProductDto productInfo = productService.findProductInfoBySeller(productId);
        return new ResponseEntity<>(productInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProductBySeller(@PathVariable Long productId){
        productService.deleteProductBySeller(productId);

        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
