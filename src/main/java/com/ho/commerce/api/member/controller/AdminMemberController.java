package com.ho.commerce.api.member.controller;

import com.ho.commerce.api.member.dto.MemberCondDto;
import com.ho.commerce.api.member.dto.MemberDtlDto;
import com.ho.commerce.api.member.dto.MemberListDto;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import com.ho.commerce.api.member.serivce.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/v1/member", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    /**
     * 관리자(ADMIN)은 사용자들(Seller + User)목록을 조회한다.
     * @param memberCondDto
     * @return ResponseEntity<Object>
     */
    @GetMapping
    public ResponseEntity<Object> findMemberListByAdmin(MemberCondDto memberCondDto){
        List<MemberListDto> memberList = memberService.findMemberListByAdmin(memberCondDto);
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    /**
     * 관리자(ADMIN)는 MemberId를 사용하여 사용자 정보를 조회할 수 있다.
     * @param String memberId
     * @return ResponseEntity<Object>
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<Object> findMemberInfoByAdmin(@PathVariable String memberId){
        MemberDtlDto memberInfo = memberService.findMemberInfoByAdmin(memberId);
        return new ResponseEntity<>(memberInfo, HttpStatus.OK);
    }

    /**
     * 관리자(ADMIN)는 MemberId를 사용하여 사용자 정보를 수정할 수 있다.
     * @param memberSaveDto
     * @return
     */
    @PutMapping
    public ResponseEntity<String> updateMemberInfoByAdmin(@RequestBody MemberSaveDto memberSaveDto){
        memberService.updateMemberInfoByAdmin(memberSaveDto);
        return new ResponseEntity<>(memberSaveDto.getMemberId(), HttpStatus.CREATED);
    }
}
