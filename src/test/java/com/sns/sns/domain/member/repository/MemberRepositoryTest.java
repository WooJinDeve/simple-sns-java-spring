package com.sns.sns.domain.member.repository;

import com.sns.sns.common.util.RepositoryTest;
import com.sns.sns.domain.member.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDate;

class MemberRepositoryTest extends RepositoryTest {
    private MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.memberRepository = new MemberRepository(namedParameterJdbcTemplate);
    }

    @Test
    @DisplayName("회원을 저장한다.")
    void 회원을_저장한다(){
        //given
        Member member = Member.builder()
                .email("email@naver.com")
                .nickname("nickname")
                .birthday(LocalDate.now())
                .build();

        //when
        Member actual = memberRepository.save(member);

        //then
        Assertions.assertThat(actual.getId()).isNotNull();
    }
}