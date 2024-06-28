package com.ho.commerce.api.member.repository;

import com.ho.commerce.api.member.dto.*;
import com.ho.commerce.common.main.login.dto.LgnDto;
import com.ho.commerce.common.main.login.dto.LgnInfoDto;
import com.ho.commerce.common.main.login.dto.QLgnInfoDto;
import com.ho.commerce.common.utils.QuerydslUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ho.commerce.api.member.domain.QMember.member;

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


    public List<MemberListDto> findMemberListByAdmin(MemberCondDto memberCondDto){
        return queryFactory
                .select(
                        new QMemberListDto(
                                member.memberId
                                , member.name
                                , member.password
                                , member.role
                        )
                )
                .from(member)
                .where(
                        QuerydslUtil.eq(member.memberId, memberCondDto.getMemberId()),
                        QuerydslUtil.eq(member.role, memberCondDto.getRole()),
                        QuerydslUtil.eq(member.name, memberCondDto.getName())
                )
                .fetch();
    }

    public MemberDtlDto findMemberInfoByAdmin(String memberId){
        return queryFactory
                .select(
                        new QMemberDtlDto(
                                member.memberId
                                , member.name
                                , member.password
                                , member.role
                                , member.moblNo
                                , member.addr
                        )
                )
                .from(member)
                .where(
                        member.memberId.eq(memberId)
                )
                .fetchFirst();
    }
}
