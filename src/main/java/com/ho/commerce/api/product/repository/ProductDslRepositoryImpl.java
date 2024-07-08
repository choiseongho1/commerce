package com.ho.commerce.api.product.repository;

import com.ho.commerce.api.option.dto.QOptionDto;
import com.ho.commerce.api.product.dto.*;
import com.ho.commerce.common.utils.QuerydslUtil;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.ho.commerce.api.option.domain.QOption.option;
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


    public ProductDto findProductInfoBySeller(Long productId){

        // Product와 Option을 조인하여 조회
        Map<Long, ProductDto> productMap = queryFactory
                .from(product)
                .leftJoin(product.options, option)
                .where(product.productId.eq(productId))
                .transform(GroupBy.groupBy(product.productId).as(
                        new QProductDto(
                                product.productId,
                                product.name,
                                product.description,
                                product.price,
                                product.stockQuantity,
                                product.category.categoryId,
                                product.imgUrl,
                                GroupBy.list(new QOptionDto(
                                        option.optionId,
                                        option.name,
                                        option.additionalPrice
                                ))
                        )
                ));

        return productMap.get(productId);
    }

    public ProductDto findProductInfoByUser(Long productId){
        return queryFactory
                .select(
                        new QProductDto(
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
                        product.productId.eq(productId)
                )
                .fetchFirst();
    }
}
