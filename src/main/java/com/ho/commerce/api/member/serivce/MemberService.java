package com.ho.commerce.api.member.serivce;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.common.exception.CustomException;
import com.ho.commerce.common.utils.EncoderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입시 memberId 존재 여부를 확인한다.
     * @param String memberId
     * @return boolean
     */
    public boolean searchIsExistMemberId(String memberId){

        // memberId로 기존 회원 정보를 조회
        Optional<Member> opMember = memberRepository.findById(memberId);

        // memberId가 이미 존재하면 Exception 실행
        if(opMember.isPresent()) throw new CustomException("이미 존재하는 회원ID입니다.");

        return true;
    }


    /**
     * 사용자의 정보를 입력받아 회원가입을 하는 service
     * @param memberSaveDto
     * @return String memberId
     */
    public String createMemberInfo(MemberSaveDto memberSaveDto){
        Member saveMember = null;
        // memberId로 기존 회원 정보를 조회
        Optional<Member> opMember = memberRepository.findById(memberSaveDto.getMemberId());

        // memberId가 이미 존재하면 Exception 실행
        if(opMember.isPresent()) {
            throw new CustomException("이미 존재하는 회원ID입니다.");
        }else{
            // 20240624 SHA256 단방향 암호화
            String encoderPwd = EncoderUtils.SHA256Decode(memberSaveDto.getPassword());
            memberSaveDto.setPassword(encoderPwd);

            Member member = memberSaveDto.toEntity();
            saveMember = memberRepository.save(member);
        }
        return saveMember.getMemberId();
    }

    
}
