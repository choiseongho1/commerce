package com.ho.commerce.common.main.login.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class LgnDto {

    private String memberId;
    private String password;

}
