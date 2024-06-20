package com.ho.commerce.api.member.repository;

import com.ho.commerce.api.member.dto.MemberListDto;
import com.ho.commerce.common.main.login.dto.LgnDto;
import com.ho.commerce.common.main.login.dto.LgnInfoDto;

import java.util.List;

public interface MemberDslRepository {

    LgnInfoDto findMemberByLgn(LgnDto lgnDto);
}
