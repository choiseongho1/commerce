package com.ho.commerce.api.product.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.repository.CategoryRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.dto.ProductSaveDto;
import com.ho.commerce.api.product.repository.ProductRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /*----------------- Admin ------------------*/

    /**
     * 판매자(Seller)가 상품(Product)를 등록한다.
     * - category 정보가 올바르지 않은 경우 Exception
     * @param productSaveDto
     * @return
     */
    /*----------------- Seller ------------------*/
    public Long createProductBySeller(ProductSaveDto productSaveDto){

        // 사용자가 선택한 category 정보를 조회한다.
        Optional<Category> opCategory = categoryRepository.findById(productSaveDto.getCategoryId());

        if(opCategory.isEmpty()) throw new CustomException("올바른 Category정보가 아닙니다.");

        Category findCategory = opCategory.orElseThrow();
        Product product = productSaveDto.toEntity();

        product.setCategory(findCategory);

        Product saveProduct = productRepository.save(product);
        return saveProduct.getProductId();
    }


    /*----------------- User ------------------*/
}
