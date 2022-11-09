package com.sns.sns.common;

import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.entity.MemberNicknameHistory;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;

public class MemberNickNameHistoryFixtures {

    public static MemberNicknameHistory createBuilderMemberNicknameHistory(Member member) {
        return MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }

    public static MemberNicknameHistory createBuilderMemberNicknameHistory(Member member, String other) {
        return MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(other)
                .build();
    }

    public static MemberNicknameHistory createMemberNicknameHistory() {
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(MemberNicknameHistory.class);
    }

    public static MemberNicknameHistory createExcludeIdByMemberNicknameHistory(){
        var param = new EasyRandomParameters().excludeField(FieldPredicates.named("id"));
        return new EasyRandom(param).nextObject(MemberNicknameHistory.class);
    }
}
