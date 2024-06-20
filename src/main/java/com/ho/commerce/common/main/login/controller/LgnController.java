package com.ho.commerce.common.main.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ho.commerce.common.exception.CustomException;
import com.ho.commerce.common.jwt.JwtUtil;
import com.ho.commerce.common.main.login.dto.LgnDto;
import com.ho.commerce.common.main.login.dto.LgnInfoDto;
import com.ho.commerce.common.main.login.service.LgnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class LgnController {

    private final JwtUtil jwtUtil;

    private final LgnService lgnService;

    /**
     * 사용자로부터 ID와 Password를 입력 받아 로그인을 시도한다.
     * @param MainLoginDto mainLoginDto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LgnDto lgnDto) throws JsonProcessingException {
        String token  = lgnService.login(lgnDto);

        return ResponseEntity.status(HttpStatus.OK).body(token);

//
    }
}
