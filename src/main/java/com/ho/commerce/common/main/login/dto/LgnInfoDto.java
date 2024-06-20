package com.ho.commerce.common.main.login.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class LgnInfoDto {

    private String memberId;
    private String name;
    private String password;
    private String role;
    private String moblNo;
    private String addr;

    @QueryProjection
    public LgnInfoDto (String memberId, String name, String password, String role){
        this.memberId = memberId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

}
