package com.ho.commerce.common.main.login.service;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailService {

    private final MemberRepository memberRepository;


}
