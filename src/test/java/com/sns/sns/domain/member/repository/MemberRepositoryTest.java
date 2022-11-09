package com.sns.sns.domain.member.repository;

import com.sns.sns.common.util.RepositoryTest;
import com.sns.sns.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;

import static com.sns.sns.common.MemberFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends RepositoryTest {
    private MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.memberRepository = new MemberRepository(namedParameterJdbcTemplate);
    }

    @Test
    @DisplayName("회원을 저장한다.")
    void 회원을_저장한다() {
        //given
        Member member = Member.builder()
                .email(이메일)
                .nickname(닉네임)
                .birthday(생일)
                .build();

        //when
        Member actual = memberRepository.save(member);

        //then
        assertThat(actual.getId()).isNotNull();
    }

    @Test
    @DisplayName("회원을 조회한다")
    void 회원을_조회한다() {
        //given
        Member member = memberRepository.save(createMember());

        //when
        Member actual = memberRepository.findById(member.getId()).get();

        //given
        assertThat(actual.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("존재하지 않는 회원을 조회한다")
    void 존재하지않는_회원을_조회한다(){
        //given & when
        Optional<Member> actual = memberRepository.findById(0L);

        //then
        assertThat(actual.isPresent()).isFalse();
    }
}