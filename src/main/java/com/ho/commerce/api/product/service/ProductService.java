package com.ho.commerce.api.product.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.repository.CategoryRepository;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.dto.ProductCondDto;
import com.ho.commerce.api.product.dto.ProductListDto;
import com.ho.commerce.api.product.dto.ProductSaveDto;
import com.ho.commerce.api.product.repository.ProductRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    /*----------------- Admin ------------------*/

    /**
     * 판매자(Seller)가 상품(Product)를 등록한다.
     * - category 정보가 올바르지 않은 경우 Exception
     * @param productSaveDto
     * @return
     */
    /*----------------- Seller ------------------*/
    public Long createProductBySeller(ProductSaveDto productSaveDto){

        // MemberId로 판매자정보를 저장한다.
        Optional<Member> opMember = memberRepository.findById(productSaveDto.getMemberId());
        if(opMember.isEmpty()) throw new CustomException("올바른 사용자 정보가 아닙니다.");

        // 사용자가 선택한 category 정보를 조회한다.
        Optional<Category> opCategory = categoryRepository.findById(productSaveDto.getCategoryId());
        if(opCategory.isEmpty()) throw new CustomException("올바른 Category정보가 아닙니다.");

        Category findCategory = opCategory.orElseThrow();
        Product product = productSaveDto.toEntity();

        product.setCategory(findCategory);

        Product saveProduct = productRepository.save(product);
        return saveProduct.getProductId();
    }

    /**
     * 판매자(Seller)가 상품(Product)를 수정한다.
     * - category 정보가 올바르지 않은 경우 Exception
     * - 상품(product) 정보가 올바르지 않은 경우 Exception
     * @param productSaveDto
     * @return
     */
    /*----------------- Seller ------------------*/
    public Long updateProductBySeller(ProductSaveDto productSaveDto){

        Optional<Product> opProduct = productRepository.findById(productSaveDto.getProductId());

        if(opProduct.isEmpty()) throw new CustomException("올바른 상품정보가 존재하지 않습니다.");


        Product findProduct = opProduct.get();
        productSaveDto.toEntity(findProduct);

        if(!productSaveDto.getCategoryId().equals(findProduct.getCategory().getCategoryId())){

            // 사용자가 선택한 category 정보를 조회한다.
            Optional<Category> opCategory = categoryRepository.findById(productSaveDto.getCategoryId());
            if(opCategory.isEmpty()) throw new CustomException("올바른 Category정보가 아닙니다.");
            Category findCategory = opCategory.orElseThrow();

            findProduct.setCategory(findCategory);
        }

        Product saveProduct = productRepository.save(findProduct);
        return saveProduct.getProductId();
    }

    /**
     * 판매자(Seller)가 등록한 상품(Product)의 목록을 조회한다.
     * @param productCondDto 
     * @return
     */
    public List<ProductListDto> findProductListBySeller(ProductCondDto productCondDto){
        return productRepository.findProductListBySeller(productCondDto);
    }

    /**
     * 판매자(Seller)가 상품(Product)를 삭제한다.
     * - 상품(product) 정보가 올바르지 않은 경우 Exception
     * @param Long productId
     */
    /*----------------- Seller ------------------*/
    public void deleteProductBySeller(Long productId){

        Optional<Product> opProduct = productRepository.findById(productId);

        if(opProduct.isEmpty()) throw new CustomException("올바른 상품정보가 존재하지 않습니다.");

        productRepository.delete(opProduct.orElseThrow());
    }


    /*----------------- User ------------------*/
    /**
     * 사용자(USER)는 등록된 상품(Product)목록을 조회한다.
     * @param ProductCondDto productCondDto
     * @return List<ProductListDto>
     */
    public List<ProductListDto> findProductListByUser(ProductCondDto productCondDto){
        return productRepository.findProductListByUser(productCondDto);
    }
}
