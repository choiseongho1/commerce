package com.ho.commerce.common.dto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
    private String memberId;

    public void setMemberIdFromRequest(HttpServletRequest request) {
        this.memberId = (String) request.getAttribute("memberId");
    }
}