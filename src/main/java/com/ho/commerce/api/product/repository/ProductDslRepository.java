package com.ho.commerce.api.product.repository;

import com.ho.commerce.api.product.dto.ProductCondDto;
import com.ho.commerce.api.product.dto.ProductDto;
import com.ho.commerce.api.product.dto.ProductListDto;

import java.util.List;

public interface ProductDslRepository {

    List<ProductListDto> findProductListBySeller(ProductCondDto productCondDto);
    List<ProductListDto> findProductListByUser(ProductCondDto productCondDto);

    ProductDto findProductInfoBySeller(Long productId);

    ProductDto findProductInfoByUser(Long productId);

}
