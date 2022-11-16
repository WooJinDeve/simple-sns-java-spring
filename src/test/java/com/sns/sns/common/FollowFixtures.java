package com.sns.sns.common;

import com.sns.sns.domain.follow.entity.Follow;
import com.sns.sns.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

public class FollowFixtures {

    public static Follow createFollow(Member toMember, Member fromMember){
        return Follow.builder()
                .toMember(toMember)
                .fromMember(fromMember)
                .build();
    }

    public static Follow createFollow(){
        var param = new EasyRandomParameters().excludeField(FieldPredicates.named("id"));
        return new EasyRandom(param).nextObject(Follow.class);
    }
}
