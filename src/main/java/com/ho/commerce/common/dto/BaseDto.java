package com.ho.commerce.common.dto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
    private String memberId;

    private Status status; // enum type for status

    public enum Status {
        NEW,
        MODIFIED,
        DELETED,
        ORIGINAL
    }


    public void setStatus(String status) {
        this.status = Status.valueOf(status.toUpperCase());
    }


    public void setMemberIdFromRequest(HttpServletRequest request) {
        this.memberId = (String) request.getAttribute("memberId");
    }
}