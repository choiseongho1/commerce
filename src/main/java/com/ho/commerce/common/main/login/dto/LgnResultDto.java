package com.ho.commerce.common.main.login.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class LgnResultDto {

    private String memberId;
    private String name;
    private String moblNo;
    private String addr;
    private String loginToken;

}
