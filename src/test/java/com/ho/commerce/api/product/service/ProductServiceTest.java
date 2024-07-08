package com.ho.commerce.api.product.service;

import com.ho.commerce.api.category.domain.Category;
import com.ho.commerce.api.category.repository.CategoryRepository;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.api.option.domain.Option;
import com.ho.commerce.api.option.dto.OptionSaveDto;
import com.ho.commerce.api.option.repository.OptionRepository;
import com.ho.commerce.api.product.domain.Product;
import com.ho.commerce.api.product.dto.ProductSaveDto;
import com.ho.commerce.api.product.repository.ProductRepository;
import com.ho.commerce.common.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
@Rollback(false)
class ProductServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OptionRepository optionRepository;


    // Product 저장 메소드 추가
    private Product defaultSaveProduct(){
        Member member = Member.builder()
                .memberId("memberId")
                .name(" A")
                .build();
        Member saveMember = memberRepository.save(member);

        Category category = Category.builder()
                .name("Category A")
                .build();
        Category saveCategory = categoryRepository.save(category);

        Product product = Product.builder()
                .name("Product A")
                .category(saveCategory)
                .member(saveMember)
                .build();

        return productRepository.save(product);
    }




    @Test
    @DisplayName("Seller가 올바른 카테고리로 제품을 등록하는 경우")
    public void testCreateProductBySeller_ValidCategory() {
        // given
        Member member = Member.builder()
                .memberId("memberId")
                .name("A")
                .build();

        Member saveMember = memberRepository.save(member);

        Category category = Category.builder()
                .name("Category A")
                .build();

        Category saveCategory = categoryRepository.save(category);

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .name("Product A")
                .categoryId(saveCategory.getCategoryId())
                .build();

        productSaveDto.setMemberId(saveMember.getMemberId());

        Product product = productSaveDto.toEntity();

        // 사용자의 Member 정보를 조회한다.
        Member findMember = memberRepository.findById(productSaveDto.getMemberId()).orElseThrow();

        product.setMember(findMember);


        // 사용자가 선택한 category 정보를 조회한다.
        Category findCategory = categoryRepository.findById(productSaveDto.getCategoryId()).orElseThrow();

        product.setCategory(findCategory);

        // when
        Product saveProduct = productRepository.save(product);

        // then
        Assertions.assertNotNull(saveProduct);
        Assertions.assertEquals(saveProduct.getCategory(), findCategory);

    }

    @Test
    @DisplayName("Seller가 올바르지 않은 카테고리로 제품을 등록하는 경우")
    public void testCreateProductBySeller_InvalidCategory() {
        // given
        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .name("Product A")
                .categoryId(2L)
                .build();

        Product product = productSaveDto.toEntity();

        // 사용자가 선택한 category 정보를 조회한다.
        Optional<Category> opCategory = categoryRepository.findById(productSaveDto.getCategoryId());

        // 예외 처리 블록 안에서 조건을 제대로 처리해야 합니다.
        CustomException exception = Assertions.assertThrows(CustomException.class, () -> {
            if (!opCategory.isPresent()) {
                throw new CustomException("올바른 Category정보가 아닙니다.");
            } else {
                product.setCategory(opCategory.get());
                Product saveProduct = productRepository.save(product);
            }
        });

        // then
        assertEquals("올바른 Category정보가 아닙니다.", exception.getMessage());
    }

    @Test
    @DisplayName("판매자(Seller)가 상품정보를 수정한다.")
    void updateProductBySeller_Valid(){
        // given
        Product product = defaultSaveProduct();

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .productId(product.getProductId())
                .name("Product B")
                .categoryId(product.getCategory().getCategoryId())
                .build();

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

        // when
        Product saveProduct = productRepository.save(findProduct);

        // then
        Assertions.assertEquals(saveProduct.getName(), "Product B");
    }


    @Test
    @DisplayName("Seller create Product with Options")
    public void testCreateProductBySellerWithOptions() {
        // given
        Member member = Member.builder()
                .memberId("memberId")
                .name("A")
                .build();

        Member saveMember = memberRepository.save(member);

        Category category = Category.builder()
                .name("Category A")
                .build();

        Category saveCategory = categoryRepository.save(category);

        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .name("Product A")
                .categoryId(saveCategory.getCategoryId())
                .build();

        List<OptionSaveDto> optionSaveDtoList = new ArrayList<>();
        for(int i = 0 ; i < 10; i++){
            OptionSaveDto optionDto = OptionSaveDto.builder()
                    .name("optionName"+i)
                    .additionalPrice(i*1000)
                    .build();

            optionSaveDtoList.add(optionDto);
        }

        productSaveDto.setOptions(optionSaveDtoList);

        productSaveDto.setMemberId(saveMember.getMemberId());

        Product product = productSaveDto.toEntity();

        // 사용자의 Member 정보를 조회한다.
        Member findMember = memberRepository.findById(productSaveDto.getMemberId()).orElseThrow();

        product.setMember(findMember);

        // 사용자가 선택한 category 정보를 조회한다.
        Category findCategory = categoryRepository.findById(productSaveDto.getCategoryId()).orElseThrow();

        product.setCategory(findCategory);

        // when
        Product saveProduct = productRepository.save(product);

        for(OptionSaveDto dto : productSaveDto.getOptions()){
            Option option = dto.toEntity();
            option.setProduct(saveProduct);

            optionRepository.save(option);
        }

        // then
        Assertions.assertNotNull(saveProduct);
        Assertions.assertEquals(saveProduct.getCategory(), findCategory);

    }

}