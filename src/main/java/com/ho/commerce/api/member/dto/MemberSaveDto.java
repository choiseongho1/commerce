package com.ho.commerce.api.member.dto;

import com.ho.commerce.api.member.domain.Member;
import com.ho.commerce.api.member.domain.MemberRole;
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

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .password(password)
                .role(role)
                .build();
    }

}
