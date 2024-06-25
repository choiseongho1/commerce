package com.ho.commerce.api.category.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.dto.CategoryListDto;
import com.ho.commerce.api.category.dto.CategorySaveDto;
import com.ho.commerce.api.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /*----------------- Admin ------------------*/

    /**
     * Admin은 Category를 저장한다.
     * @param categorySaveDto
     * @return Long categoryId
     */
    public Long createCategory(CategorySaveDto categorySaveDto){
        // 사용자로부터 받은 Category 정보를 Entity로 build
        Category category = categorySaveDto.toEntity();
        Category saveCategory = categoryRepository.save(category);
        return saveCategory.getCategoryId();
    }


    /*----------------- Seller ------------------*/
    public List<CategoryListDto> findCategoryList(){
        return categoryRepository.findCategoryList();
    }
}
