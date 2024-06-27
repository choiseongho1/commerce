package com.ho.commerce.api.category.controller;

import com.ho.commerce.api.category.dto.CategoryListDto;
import com.ho.commerce.api.category.dto.CategorySaveDto;
import com.ho.commerce.api.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 관리자(Admin)는 Category를 조회한다.
     * @param categorySaveDto
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> findCategoryList(){
        List<CategoryListDto> categoryList = categoryService.findCategoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /**
     * 관리자(Admin)은 Category 정보를 수정한다.
     * @param categorySaveDto
     * @return Long categoryId
     */
    @PutMapping
    public ResponseEntity<Long> updateCategoryInfo(@RequestBody CategorySaveDto categorySaveDto){
        Long categoryId = categoryService.updateCategoryInfo(categorySaveDto);
        return new ResponseEntity<>(categoryId, HttpStatus.CREATED);
    }

}
