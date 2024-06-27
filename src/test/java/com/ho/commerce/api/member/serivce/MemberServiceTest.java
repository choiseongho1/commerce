package com.ho.commerce.api.member.serivce;

import com.ho.commerce.api.cart.domain.Cart;
import com.ho.commerce.api.cart.repository.CartRepository;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(false)
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired CartRepository cartRepository;

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


    @Test
    @DisplayName("(성공)일반 사용자가 회원가입을 한다.")
    public void createMemberBySuccess(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("userId")
                .name("name")
                .password("password")
                .role("USER")
                .build();

        Member member = memberSaveDto.toEntity();

        // when
        Member saveMember = memberRepository.save(member);


        // then
        // 회원가입이 정상적으로 이루어졌는지 확인하기
        assertEquals("userId", saveMember.getMemberId());

        // 권한이 UESR로 저장되었는지 확인하기
        assertEquals("USER", saveMember.getRole());
    }

    @Test
    @DisplayName("(실패)일반 사용자가 회원가입을 한다.")
    public void createMemberByFail(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("userId")
                .name("name")
                .password("password")
                .role("USER")
                .build();

        Member member = memberSaveDto.toEntity();
        memberRepository.save(member);

        // given
        MemberSaveDto newMemberDto = MemberSaveDto.builder()
                .memberId("userId")
                .name("newMember")
                .password("password")
                .role("USER")
                .build();

        // when

        Member saveMember = null;
        Optional<Member> opMember = memberRepository.findById(newMemberDto.getMemberId());


        // Use assertThrows to verify that CustomException is thrown
        CustomException exception = assertThrows(CustomException.class, () -> {
            if (opMember.isPresent()) {
                throw new CustomException("이미 존재하는 회원ID입니다.");
            }else{
                Member newMember = memberSaveDto.toEntity();
                memberRepository.save(newMember);
            }
        });

        // Optionally, you can assert the exception message if needed
        assertEquals("이미 존재하는 회원ID입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("(성공)일반 사용자가 회원가입시 장바구니(Cart)가 생성되는지 확인한다.")
    public void createMemberWithCart(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("userId")
                .name("name")
                .password("password")
                .role("USER")
                .build();

        Member member = memberSaveDto.toEntity();

        // when
        Member saveMember = memberRepository.save(member);

        Cart saveCart = cartRepository.save(Cart.builder().member(saveMember).build());

        // then
        // 회원가입이 정상적으로 이루어졌는지 확인하기
        assertEquals("userId", saveMember.getMemberId());

        // 장바구니가 정상적으로 생성되었는지 확인하기
        assertFalse(Objects.isNull(saveCart.getCartId()));
    }

}