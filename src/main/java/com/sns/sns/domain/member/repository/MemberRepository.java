package com.sns.sns.domain.member.repository;

import com.sns.sns.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByIdIn(List<Long> ids);

    default Member getMember(Long memberId){
        return findById(memberId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
