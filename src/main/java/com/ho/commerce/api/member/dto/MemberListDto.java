package com.ho.commerce.api.member.dto;

import com.ho.commerce.api.member.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberListDto {

    private String memberId;
    private String name;
    private String password;
    private String role;
    private String moblNo;
    private String addr;

    @QueryProjection
    public MemberListDto (String memberId, String name, String password, String role){
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

}
