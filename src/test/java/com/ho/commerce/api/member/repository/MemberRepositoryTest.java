package com.ho.commerce.api.member.repository;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Transactional
@SpringBootTest
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("사용자가 DB에 정상적으로 저장이 되는지 저장")
    void saveMember(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("memberId")
                .name("name")
                .password("password")
                .build();

        Member member = memberSaveDto.toEntity();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Assertions.assertEquals(saveMember.getName(), member.getName());
    }


    @Test
    @DisplayName("사용자의 정보 저장 시 CreatedAt 등 기본 정보가 저장되는지 확인 ")
    void saveMemberJpaAuditing(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("memberId")
                .name("name")
                .password("password")
                .role("ADMIN")
                .build();

        Member member = memberSaveDto.toEntity();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Assertions.assertTrue(!Objects.isNull(saveMember.getCreatedDate()));
        Assertions.assertTrue(!Objects.isNull(saveMember.getCreateBy()));
        Assertions.assertTrue(!Objects.isNull(saveMember.getLastModifedBy()));
        Assertions.assertTrue(!Objects.isNull(saveMember.getModifiedDate()));
    }

    @Test
    @DisplayName("Member Entity에 Persistable을 추가하여 isNew처리가 되는지 확인 ")
    void memberPersistable(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .memberId("memberId")
                .name("name")
                .password("password")
                .role("ADMIN")
                .build();

        Member member = memberSaveDto.toEntity();

        // when
        Member saveMember = memberRepository.save(member);

        saveMember.setName("newName");
        Member newSaveMember = memberRepository.save(saveMember);

        // then
        Assertions.assertEquals("newName", newSaveMember.getName());
        Assertions.assertEquals(1, memberRepository.findAll().size());
    }

}