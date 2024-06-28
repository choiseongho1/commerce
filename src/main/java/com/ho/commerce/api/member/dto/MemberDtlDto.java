package com.ho.commerce.api.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class MemberDtlDto {

    private String memberId;
    private String name;
    private String password;
    private String role; // 관리자, 판매자, 사용자

    /* 20240620 전화번호, 주소 추가 */
    private String moblNo;
    private String addr;

    @QueryProjection
    public MemberDtlDto (String memberId, String name, String password, String role, String moblNo, String addr){
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.moblNo = moblNo;
        this.addr = addr;
    }

}
