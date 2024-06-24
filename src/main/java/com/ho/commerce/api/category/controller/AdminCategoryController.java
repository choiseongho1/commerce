package com.ho.commerce.api.category.controller;

import com.ho.commerce.api.category.dto.CategorySaveDto;
import com.ho.commerce.api.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/api/v1/category", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    /**
     * Admin은 Category를 저장한다.
     * @param categorySaveDto
     * @return
     */
    @PostMapping
    public ResponseEntity<Long> createCategoryInfo(@RequestBody CategorySaveDto categorySaveDto){
        Long categoryId = categoryService.createCategory(categorySaveDto);
        return new ResponseEntity<>(categoryId, HttpStatus.CREATED);
    }
}
