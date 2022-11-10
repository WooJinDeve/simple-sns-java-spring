package com.sns.sns.domain.member.repository;

import com.sns.sns.common.util.RepositoryTest;
import com.sns.sns.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
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
        Member member = memberRepository.save(createBuilderMember());

        //when
        Member actual = memberRepository.findById(member.getId()).get();

        //given
        assertThat(actual.getId()).isEqualTo(member.getId());
    }

    @Test
    @DisplayName("존재하지 않는 회원을 조회한다")
    void 존재하지않는_회원을_조회한다() {
        //given & when
        Optional<Member> actual = memberRepository.findById(0L);

        //then
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    @DisplayName("존재하지 않는 회원아이디로 전체 조회시 빈 리스트를 반환한다.")
    void 존재하지않는_회원아이디로_전체_조회시_빈_리스트를_반환한다(){
        //given
        List<Long> ids = List.of();

        //when
        List<Member> actual = memberRepository.findAllByIdIn(ids);

        //then
        assertThat(actual.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("회원 아이디가 저장된 리스트로 전체 조회를 한다.")
    void 회원_아이디가_저장된_리스트로_전체조회를_한다(){
        //given
        Long id1 = memberRepository.save(createBuilderMember()).getId();
        Long id2 = memberRepository.save(createBuilderMember()).getId();
        List<Long> ids = List.of(id1, id2);

        //when
        List<Member> actual = memberRepository.findAllByIdIn(ids);

        //then
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("존재하는 회원 아이디와 존재하지 않는 회원 아이디로 전체조회를 했을때 존재하는 회원만 반환된다.")
    void 존재하는_회원_아이디와_존재하지_않는_회원_아이디로_전체조회를_했을때_존재하는_회원만_반환된다(){
        //given
        Long id1 = memberRepository.save(createBuilderMember()).getId();
        Long id2 = 0L;
        List<Long> ids = List.of(id1, id2);

        //when
        List<Member> actual = memberRepository.findAllByIdIn(ids);

        //then
        assertThat(actual.size()).isEqualTo(1);
    }
}