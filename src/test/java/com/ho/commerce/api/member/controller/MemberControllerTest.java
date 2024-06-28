package com.ho.commerce.api.member.controller;

import com.ho.commerce.api.member.serivce.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminMemberController.class)
class MemberControllerTest {

    /**
     * 웹 API 테스트할 때 사용
     * 스프링 MVC 테스트의 시작점
     * HTTP GET,POST 등에 대해 API 테스트 가능
     * */
    @Autowired
    MockMvc mvc;

    /*
    * IoC환경에 bean 등록
    */

    @MockBean
    MemberService memberService;


    static final String BASE_URL = "/api/v1/member";

    @Test
    @DisplayName("사용자에게 MemberId를 입력받아 해당 ID가 사용가능한지 테스트(성공)")
    void searchIsExistMemberIdSuccess() throws Exception {
        //given

        //when
        final ResultActions actions =
                mvc.perform(
                        get(BASE_URL+ "/valid/{memberId}", "memberId"));

        //then
        actions
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("사용자에게 MemberId를 입력받아 해당 ID가 사용가능한지 테스트(실패)")
    void searchIsExistMemberIdFail() throws Exception {
       /*
       // Given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("memberId")
                .name("name")
                .password("password")
                .role("USER")
                .build();


        given(memberService.createMemberInfo(any(MemberSaveDto.class))).willReturn("");


        // When
        final ResultActions actions =
                mvc.perform(
                        get(BASE_URL + "/valid/{memberId}", memberSaveDto.getMemberId()));

        // Then
        actions
                .andExpect(status().isOk());
        */
    }
}