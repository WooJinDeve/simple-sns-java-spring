package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.sns.sns.common.MemberFixtures.createMemberCommand;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberWriteServiceTest {
    @Autowired
    private MemberWriteService memberWriteService;

    @Test
    @DisplayName("회원정보를 정상적으로 저장한다")
    void 회원정보를_정상적으로_저장한다(){
        //given & when
        Member actual = memberWriteService.create(createMemberCommand());

        //then
        assertThat(actual.getId()).isNotNull();
    }
}
