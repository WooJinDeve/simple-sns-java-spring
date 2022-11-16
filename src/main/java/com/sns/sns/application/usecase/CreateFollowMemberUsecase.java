package com.sns.sns.application.usecase;

import com.sns.sns.domain.follow.service.FollowWriteService;
import com.sns.sns.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowMemberUsecase {
    private final MemberRepository memberRepository;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId){
        var fromMember = memberRepository.getMember(fromMemberId);
        var toMember = memberRepository.getMember(toMemberId);

        followWriteService.create(fromMember,toMember);
    }
}
