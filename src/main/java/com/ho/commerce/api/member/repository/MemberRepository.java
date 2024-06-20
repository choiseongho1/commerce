package com.ho.commerce.api.member.repository;

import com.ho.commerce.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String>, MemberDslRepository {
}
