package com.ho.commerce.api.category.repository;

import com.ho.commerce.api.category.dto.CategoryListDto;

import java.util.List;

public interface CategoryDslRepository {

    List<CategoryListDto> findCategoryList();
}
