package com.sns.sns.common;

import com.sns.sns.domain.follow.entity.Follow;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

public class FollowFixtures {

    public static Follow createFollow(Long toMember, Long fromMember){
        return Follow.builder()
                .toMemberId(toMember)
                .fromMemberId(fromMember)
                .build();
    }

    public static Follow createFollow(){
        var param = new EasyRandomParameters().excludeField(FieldPredicates.named("id"));
        return new EasyRandom(param).nextObject(Follow.class);
    }
}
