package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository memberRepository;

    public Member getMember(Long id){
        return memberRepository.findById(id)
                .orElseThrow();
    }
}
