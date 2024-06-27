package com.ho.commerce.api.category.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.dto.CategoryListDto;
import com.ho.commerce.api.category.dto.CategorySaveDto;
import com.ho.commerce.api.category.repository.CategoryRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * 관리자(Admin)은 Category 정보를 수정한다.
     * @param categorySaveDto
     * @return Long categoryId
     */
    public Long updateCategoryInfo(CategorySaveDto categorySaveDto){

        // 사용자가 선택한 category 정보를 조회한다.
        Optional<Category> opCategory = categoryRepository.findById(categorySaveDto.getCategoryId());
        if(opCategory.isEmpty()) throw new CustomException("올바른 Category정보가 아닙니다.");

        Category findCategory = opCategory.orElseThrow();

        categorySaveDto.toEntity(findCategory);
        Category saveCategory = categoryRepository.save(findCategory);
        return saveCategory.getCategoryId();
    }


    /*----------------- Seller ------------------*/
    public List<CategoryListDto> findCategoryList(){
        return categoryRepository.findCategoryList();
    }
}
