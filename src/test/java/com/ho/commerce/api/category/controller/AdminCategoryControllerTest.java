package com.ho.commerce.api.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ho.commerce.api.category.dto.CategorySaveDto;
import com.ho.commerce.api.category.service.CategoryService;
import com.ho.commerce.common.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminCategoryController.class)
@Import(TestSecurityConfig.class) // 테스트용 보안 설정을 가져옵니다.
class AdminCategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    static final String BASE_URL = "/admin/api/v1/category";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Admin사용자가 category를 등록한다.")
    public void createCategoryInfo() throws Exception {
        // Given
        CategorySaveDto categorySaveDto = new CategorySaveDto();
        categorySaveDto.setName("Category A");

        Long categoryId = 1L;

        when(categoryService.createCategory(any(CategorySaveDto.class))).thenReturn(categoryId);

        // When
        ResultActions resultActions = mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categorySaveDto)));

        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(categoryId));
    }
}