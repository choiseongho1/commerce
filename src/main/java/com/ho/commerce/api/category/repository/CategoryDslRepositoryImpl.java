package com.ho.commerce.api.category.repository;

import com.ho.commerce.api.category.dto.CategoryListDto;
import com.ho.commerce.api.category.dto.QCategoryListDto;
import com.ho.commerce.common.utils.QuerydslUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ho.commerce.api.category.domain.QCategory.category;

@RequiredArgsConstructor
public class CategoryDslRepositoryImpl implements CategoryDslRepository {

    private final JPAQueryFactory queryFactory;


    public List<CategoryListDto> findCategoryList(){
        return queryFactory
                .select(
                        new QCategoryListDto(
                                category.categoryId,
                                category.name
                        )
                )
                .from(category)
                .where(
                        QuerydslUtil.eq(category.delYn, "N")
                )
                .fetch();
    }
}
