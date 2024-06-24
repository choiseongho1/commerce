package com.ho.commerce.api.category.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.dto.CategorySaveDto;
import com.ho.commerce.api.category.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("Admin인 사용자가 category를 생성한다.")
    void createCategoryForAdmin(){
        // given
        CategorySaveDto categorySaveDto = CategorySaveDto.builder()
                .name("category Name 1")
                .build();

        Category saveCategory = categorySaveDto.toEntity();

        // when
        Category resultCategory = categoryRepository.save(saveCategory);

        // then
        Assertions.assertEquals(resultCategory.getName(), categorySaveDto.getName());
    }

}