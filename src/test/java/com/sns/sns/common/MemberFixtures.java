package com.sns.sns.common;

import com.sns.sns.domain.member.dto.RegisterMemberCommand;

import java.time.LocalDate;

public class MemberFixtures {

    public static final String 이메일 = "email@naver.com";
    public static final String 닉네임 = "닉네임";
    public static final LocalDate 생일 = LocalDate.now();

    public static RegisterMemberCommand createMemberCommand(){
        return new RegisterMemberCommand(이메일, 닉네임, 생일);
    }
}
