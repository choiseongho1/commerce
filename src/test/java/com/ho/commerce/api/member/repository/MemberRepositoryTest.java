package com.ho.commerce.api.member.repository;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository userRepository;

    @Test
    @DisplayName("사용자가 DB에 정상적으로 저장이 되는지 저장")
    void saveUser(){
        // given
        MemberSaveDto memberSaveDto = MemberSaveDto.builder()
                .name("name")
                .password("password")
                .role("admin")
                .build();

        Member member = memberSaveDto.toEntity();

        // when
        Member saveMember = userRepository.save(member);

        // then
        Assertions.assertEquals(saveMember.getName(), member.getName());
    }
}