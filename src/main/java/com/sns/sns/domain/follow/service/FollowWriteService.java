package com.sns.sns.domain.follow.service;

import com.sns.sns.domain.follow.entity.Follow;
import com.sns.sns.domain.follow.repository.FollowRepository;
import com.sns.sns.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;

    public void create(Member fromMember, Member toMember) {
        validateSave(fromMember, toMember);

        var follow = Follow.builder()
                .fromMember(fromMember)
                .toMember(toMember)
                .build();
        followRepository.save(follow);
    }

    private void validateSave(Member fromMember, Member toMember) {
        Assert.isTrue(!fromMember.getId().equals(toMember.getId()), "동일한 회원입니다.");
        followRepository.validateExistByFromMemberAndToMember(fromMember, toMember);
    }
}
