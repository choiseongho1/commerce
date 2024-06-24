package com.ho.commerce.common.main.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import com.ho.commerce.api.member.serivce.MemberService;
import com.ho.commerce.common.main.login.dto.LgnDto;
import com.ho.commerce.common.main.login.service.LgnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/default")
public class LgnController {

    private final LgnService lgnService;
    private final MemberService memberService;

    /**
     * 사용자로부터 ID와 Password를 입력 받아 로그인을 시도한다.
     * @param MainLoginDto mainLoginDto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LgnDto lgnDto) throws JsonProcessingException {
        String token  = lgnService.login(lgnDto);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    /**
     * 사용자로부터 MemberId를 입력받아 해당 아이디가 사용가능한지 확인하는 controller
     * @param memberId
     * @return ResponseEntity<Boolean>
     */
    @GetMapping("/valid/{memberId}")
    public ResponseEntity<Boolean> searchIsExistMemberId(@PathVariable String memberId){
        boolean useMemberId = memberService.searchIsExistMemberId(memberId);
        return new ResponseEntity<>(useMemberId, HttpStatus.OK);
    }

    /**
     * 사용자로부터 정보를 입력받아 해당 정보로 회원 가입을 한다.
     * @param memberSaveDto
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createMemberInfo(@RequestBody MemberSaveDto memberSaveDto){
        memberService.createMemberInfo(memberSaveDto);
        return new ResponseEntity<>(memberSaveDto.getMemberId(), HttpStatus.CREATED);
    }


}
