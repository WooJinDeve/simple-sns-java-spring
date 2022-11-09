package com.sns.sns.common;

import com.sns.sns.domain.member.dto.RegisterMemberCommand;
import com.sns.sns.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

public class MemberFixtures {

    public static final String 이메일 = "email@naver.com";
    public static final String 닉네임 = "닉네임";
    public static final LocalDate 생일 = LocalDate.now();

    public static RegisterMemberCommand createMemberCommand() {
        return new RegisterMemberCommand(이메일, 닉네임, 생일);
    }

    public static Member createBuilderMember() {
        return Member.builder()
                .email(이메일)
                .nickname(닉네임)
                .birthday(생일)
                .build();
    }

    public static Member createMember() {
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);
    }

    public static Member createMember(Long seed) {
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }


}
