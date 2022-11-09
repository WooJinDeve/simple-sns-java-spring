package com.sns.sns.common;

import com.sns.sns.domain.member.entity.MemberNicknameHistory;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberNickNameHistoryFixtures {

    public static MemberNicknameHistory createMember() {
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(MemberNicknameHistory.class);
    }
}
