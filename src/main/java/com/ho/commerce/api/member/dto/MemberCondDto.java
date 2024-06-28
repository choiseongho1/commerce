package com.ho.commerce.api.member.dto;

import lombok.*;

@Builder
@Getter
@Setter
public class MemberCondDto {

    private String memberId;
    private String name;
    private String role;

}
