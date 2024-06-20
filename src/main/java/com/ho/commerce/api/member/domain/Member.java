package com.ho.commerce.api.member.domain;

import com.ho.commerce.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity implements Persistable<String>{

    @Id
    private String memberId;

    private String name;
    private String password;
    private String role; // 관리자, 판매자, 사용자

    /* 20240620 전화번호, 주소 추가 */
    private String moblNo;
    private String addr;

    @Override
    public String getId() {
        return this.memberId;
    }

    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null ;
    }
}


