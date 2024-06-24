package com.ho.commerce.api.category.repository;

import com.ho.commerce.api.category.domain.Category;
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
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Category저장시에 delYn의 default Value가 정상적으로 N으로 저장 되는지 확인한다.")
    void saveCategory(){
        // given
        Category category = Category.builder()
                .name("Category A")
                .build();

        // when
        Category saveCategory = categoryRepository.save(category);

        // then
        Assertions.assertEquals(saveCategory.getDelYn(), "N");
        Assertions.assertEquals(saveCategory.getName(), "Category A");

    }
}