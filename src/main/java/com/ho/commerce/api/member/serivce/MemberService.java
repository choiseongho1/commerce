package com.ho.commerce.api.member.serivce;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean searchIsExistMemberId(String memberId){

        // memberId로 기존 회원 정보를 조회
        Optional<Member> opMember = memberRepository.findById(memberId);

        // memberId가 이미 존재하면 Exception 실행
        if(opMember.isPresent()) throw new CustomException("이미 존재하는 회원ID입니다.");

        return true;
    }

    
}
