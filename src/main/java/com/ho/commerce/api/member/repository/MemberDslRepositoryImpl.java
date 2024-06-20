package com.ho.commerce.api.member.repository;

import static com.ho.commerce.api.member.domain.QMember.member;


import com.ho.commerce.api.member.dto.QMemberListDto;
import com.ho.commerce.common.main.login.dto.LgnDto;
import com.ho.commerce.common.main.login.dto.LgnInfoDto;
import com.ho.commerce.common.main.login.dto.QLgnInfoDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberDslRepositoryImpl implements MemberDslRepository {

    private final JPAQueryFactory queryFactory;


    public LgnInfoDto findMemberByLgn(LgnDto lgnDto){
        return queryFactory
                .select(
                        new QLgnInfoDto(
                                member.memberId
                                , member.name
                                , member.password
                                , member.role
                        )
                )
                .from(member)
                .where(
                        member.memberId.eq(lgnDto.getMemberId()),
                        member.password.eq(lgnDto.getPassword())
                )
                .fetchFirst();
    }
}
