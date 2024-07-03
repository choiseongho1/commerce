package com.ho.commerce.api.category.controller;

import com.ho.commerce.api.category.dto.CategoryListDto;
import com.ho.commerce.api.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/seller/api/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SellerCategoryController {

    private final CategoryService categoryService;

    /**
     * 판매자(Seller)는 Category를 조회한다.
     * @param null
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> findCategoryList(){
        List<CategoryListDto> categoryList = categoryService.findCategoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

}
