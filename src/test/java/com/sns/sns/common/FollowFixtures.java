package com.sns.sns.common;

import com.sns.sns.domain.follow.entity.Follow;

public class FollowFixtures {

    public static Follow createFollow(Long toMember, Long fromMember){
        return Follow.builder()
                .toMemberId(toMember)
                .fromMemberId(fromMember)
                .build();
    }
}
