package com.ho.commerce.api.product.repository;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.repository.CategoryRepository;
import com.ho.commerce.api.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(false)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("product를 저장할때, category정보가 정상적으로 저장되는지 확인한다.")
    void saveProductTest(){
        // given
        Category category = Category.builder()
                .name("Category A")
                .build();
        Category saveCategory = categoryRepository.save(category);

        Product product = Product.builder()
                .name("Product A")
                .description("설명")
                .price(1000)
                .stockQuantity(100)
                .category(saveCategory)
                .build();

        // when
        Product saveProduct = productRepository.save(product);

        // then
        Assertions.assertEquals(saveProduct.getName(),"Product A");
        Assertions.assertEquals(saveProduct.getCategory().getName(),"Category A");

    }
}