package com.ho.commerce.api.member.serivce;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.common.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
@Rollback(false)
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;


    @Test
    @DisplayName("아이디 중복을 확인하는 메소드")
    public void searchIsExistMemberId(){
        // given
        Member member = Member.builder()
                .memberId("id")
                .name("name")
                .password("password")
                .role("ADMIN")
                .build();

        memberRepository.save(member);


        // when
        Optional<Member> opMember = memberRepository.findById("id");

        // Use assertThrows to verify that CustomException is thrown
        CustomException exception = assertThrows(CustomException.class, () -> {
            if (opMember.isPresent()) {
                throw new CustomException("이미 존재하는 회원ID입니다.");
            }
        });

        // Optionally, you can assert the exception message if needed
        assertEquals("이미 존재하는 회원ID입니다.", exception.getMessage());
    }

}