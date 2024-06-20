package com.ho.commerce.api.member.controller;

import com.ho.commerce.api.member.dto.MemberSaveDto;
import com.ho.commerce.api.member.serivce.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/member", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
