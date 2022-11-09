package com.sns.sns.application.usecase;

import com.sns.sns.domain.follow.entity.Follow;
import com.sns.sns.domain.follow.service.FollowReadService;
import com.sns.sns.domain.member.dto.MemberDto;
import com.sns.sns.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetFollowingMemberUsecase {

    final private MemberReadService memberReadService;
    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).collect(Collectors.toList());
        return memberReadService.getMembers(followingMemberIds);
    }
}
