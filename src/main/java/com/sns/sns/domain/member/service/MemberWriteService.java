package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.dto.RegisterMemberCommand;
import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;

    /*
     * 요구 사항 - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
     *          - 닉네임은 10자를 넘길 수 없다.
     * 파라미터 - memberRegisterCommand
     *
     * Member member = Member.of(memberRegisterComment)
     * memberRepository.save(member)
     * */
    public Member create(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        return memberRepository.save(member);
    }
}
