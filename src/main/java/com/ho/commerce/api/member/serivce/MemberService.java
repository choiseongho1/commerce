package com.ho.commerce.api.member.serivce;

import com.ho.commerce.api.cart.domain.Cart;
import com.ho.commerce.api.cart.repository.CartRepository;
import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.dto.MemberCondDto;
import com.ho.commerce.api.member.dto.MemberDtlDto;
import com.ho.commerce.api.member.dto.MemberListDto;
import com.ho.commerce.api.member.dto.MemberSaveDto;
import com.ho.commerce.api.member.repository.MemberRepository;
import com.ho.commerce.common.exception.CustomException;
import com.ho.commerce.common.utils.EncoderUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;

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

        // 사용자의 역할이 USER인 경우 장바구니(CART) 만들어주기
        if(memberSaveDto.getRole().equals("USER")){
            cartRepository.save(Cart.builder().member(saveMember).build());
        }


        return saveMember.getMemberId();
    }



    /* ----------------- admin ---------------------*/

    /**
     * 관리자(ADMIN)은 사용자들(Seller + User)목록을 조회한다.
     * @param memberCondDto
     * @return List<MemberListDto>
     */
    public List<MemberListDto> findMemberListByAdmin(MemberCondDto memberCondDto){
        return memberRepository.findMemberListByAdmin(memberCondDto);
    }

    /**
     * 관리자(ADMIN)는 MemberId를 사용하여 사용자 정보를 조회할 수 있다.
     * @param String memberId
     * @return MemberDtlDto
     */
    public MemberDtlDto findMemberInfoByAdmin(String memberId){
        if(StringUtils.isEmpty(memberId)) {
            throw new CustomException("MemberId가 존재하지 않습니다.");
        }

        return memberRepository.findMemberInfoByAdmin(memberId);
    }

    /**
     * 관리자(ADMIN)는 MemberId를 사용하여 사용자 정보를 조회할 수 있다.
     * @param String memberId
     */
    public void updateMemberInfoByAdmin(MemberSaveDto memberSaveDto){
        // memberId로 기존 회원 정보를 조회
        Optional<Member> opMember = memberRepository.findById(memberSaveDto.getMemberId());

        // memberId가 이미 존재하면 Exception 실행
        if(!opMember.isPresent()) throw new CustomException("MemberId가 존재하지 않습니다.");

        Member findMember = opMember.orElseThrow();
        memberSaveDto.toEntity(findMember);
        memberRepository.save(findMember);

    }
    
}
