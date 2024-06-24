package com.ho.commerce.common.main.login.service;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.common.exception.CustomException;
import com.ho.commerce.common.jwt.JwtUtil;
import com.ho.commerce.common.main.login.dto.LgnDto;
import com.ho.commerce.common.main.login.dto.LgnInfoDto;
import com.ho.commerce.common.utils.EncoderUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LgnService {

    private final JwtUtil jwtUtil;

    @Autowired
    private MemberRepository memberRepository;

    public String login(LgnDto lgnDto) {

        String memberId = lgnDto.getMemberId();
        String password = lgnDto.getPassword();
        Optional<Member> opMember = memberRepository.findById(memberId);

        String encoderPwd = EncoderUtils.SHA256Decode(password);
        lgnDto.setPassword(encoderPwd);

        if(opMember.isEmpty()) throw new CustomException("회원 ID가 존재하지 않습니다.");

        if(!StringUtils.equals(encoderPwd, opMember.get().getPassword())) {
            throw new CustomException("비밀번호가 일치하지 않습니다.");
        }

        LgnInfoDto lgnInfoDto = memberRepository.findMemberByLgn(lgnDto);

        return jwtUtil.createAccessToken(lgnInfoDto);

    }

}
