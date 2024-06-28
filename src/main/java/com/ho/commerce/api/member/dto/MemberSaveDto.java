package com.ho.commerce.api.member.dto;

import com.ho.commerce.api.member.domain.Member;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberSaveDto {

    private String memberId;
    private String name;
    private String password;
    private String role; // 관리자, 판매자, 사용자

    /* 20240620 전화번호, 주소 추가 */
    private String moblNo;
    private String addr;

    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .name(name)
                .password(password)
                .role(role)
                .moblNo(moblNo)
                .addr(addr)
                .build();
    }

    public void toEntity(Member member){
        if(member == null ) return;
        member.setRole(this.role);
        member.setMoblNo(this.moblNo);
        member.setAddr(this.addr);

    }

}
