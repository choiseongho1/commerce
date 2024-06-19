package com.ho.commerce.api.member.domain;

import com.ho.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {

    @Id
    private String memberId;

    private String name;
    private String password;
    private String role; // 관리자, 판매자, 사용자
}


