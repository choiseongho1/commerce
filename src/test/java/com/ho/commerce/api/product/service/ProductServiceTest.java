package com.ho.commerce.api.product.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.repository.CategoryRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.dto.ProductSaveDto;
import com.ho.commerce.api.product.repository.ProductRepository;
import com.ho.commerce.common.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
@Rollback(false)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    @DisplayName("Seller가 올바른 카테고리로 제품을 등록하는 경우")
    public void testCreateProductBySeller_ValidCategory() {
        // given
        Category category = Category.builder()
                .name("Category A")
                .build();

        Category saveCategory = categoryRepository.save(category);

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .name("Product A")
                .categoryId(saveCategory.getCategoryId())
                .build();

        Product product = productSaveDto.toEntity();

        // 사용자가 선택한 category 정보를 조회한다.
        Category findCategory = categoryRepository.findById(productSaveDto.getCategoryId()).orElseThrow();

        product.setCategory(findCategory);

        // when
        Product saveProduct = productRepository.save(product);

        // then
        Assertions.assertNotNull(saveProduct);
        Assertions.assertEquals(saveProduct.getCategory(), findCategory);

    }

    @Test
    @DisplayName("Seller가 올바르지 않은 카테고리로 제품을 등록하는 경우")
    public void testCreateProductBySeller_InvalidCategory() {
        // given
        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .name("Product A")
                .categoryId(2L)
                .build();

        Product product = productSaveDto.toEntity();

        // 사용자가 선택한 category 정보를 조회한다.
        Optional<Category> opCategory = categoryRepository.findById(productSaveDto.getCategoryId());

        // 예외 처리 블록 안에서 조건을 제대로 처리해야 합니다.
        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
            if (!opCategory.isPresent()) {
                throw new CustomException("올바른 Category정보가 아닙니다.");
            } else {
                product.setCategory(opCategory.get());
                Product saveProduct = productRepository.save(product);
            }
        });

        // then
        assertEquals("올바른 Category정보가 아닙니다.", exception.getMessage());
    }

}