package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.dto.RegisterMemberCommand;
import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.entity.MemberNicknameHistory;
import com.sns.sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.sns.sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;


    @Transactional
    public Member create(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        Member saveMember = memberRepository.save(member);
        saveMemberNicknameHistory(saveMember);
        return saveMember;
    }

    public void changeNickname(Long memberId, String nickname){
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);
        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }
}
