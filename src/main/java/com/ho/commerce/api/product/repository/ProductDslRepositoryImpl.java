package com.ho.commerce.api.product.repository;

import com.ho.commerce.api.product.dto.ProductCondDto;
import com.ho.commerce.api.product.dto.ProductListDto;
import com.ho.commerce.api.product.dto.QProductListDto;
import com.ho.commerce.common.utils.QuerydslUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ho.commerce.api.product.domain.QProduct.product;


@RequiredArgsConstructor
public class ProductDslRepositoryImpl implements ProductDslRepository {

    private final JPAQueryFactory queryFactory;


    public List<ProductListDto> findProductListBySeller(ProductCondDto productCondDto){
        return queryFactory
                .select(
                        new QProductListDto(
                                product.productId,
                                product.name,
                                product.description,
                                product.price,
                                product.stockQuantity,
                                product.category.categoryId,
                                product.imgUrl
                        )
                )
                .from(product)
                .where(
                        QuerydslUtil.eq(product.category.categoryId, productCondDto.getCategoryId()),
                        QuerydslUtil.eq(product.member.memberId, productCondDto.getMemberId())
                )
                .fetch();
    }

    public List<ProductListDto> findProductListByUser(ProductCondDto productCondDto){
        return queryFactory
                .select(
                        new QProductListDto(
                                product.productId,
                                product.name,
                                product.description,
                                product.price,
                                product.stockQuantity,
                                product.category.categoryId,
                                product.imgUrl
                        )
                )
                .from(product)
                .where(
                        QuerydslUtil.eq(product.category.categoryId, productCondDto.getCategoryId())
                )
                .fetch();
    }
}
