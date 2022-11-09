package com.sns.sns.domain.member.repository;

import com.sns.sns.common.util.RepositoryTest;
import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.entity.MemberNicknameHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static com.sns.sns.common.MemberFixtures.createBuilderMember;
import static com.sns.sns.common.MemberNickNameHistoryFixtures.createBuilderMemberNicknameHistory;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberNicknameHistoryRepositoryTest extends RepositoryTest {
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    private MemberRepository memberRepository;

    @Autowired
    public MemberNicknameHistoryRepositoryTest(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.memberNicknameHistoryRepository = new MemberNicknameHistoryRepository(namedParameterJdbcTemplate);
        this.memberRepository = new MemberRepository(namedParameterJdbcTemplate);
    }

    @Test
    @DisplayName("닉네임 변경 내역이 정상적으로 저장된다.")
    void 닉네임_변경_내역이_정상적으로_저장된다(){
        //given
        Member member = memberRepository.save(createBuilderMember());
        String expected = "변경닉네임";

        //when & then
        assertDoesNotThrow(() -> memberNicknameHistoryRepository.save(createBuilderMemberNicknameHistory(member, expected)));
    }

    @Test
    @DisplayName("닉네임 변경 이력을 회원의 고유 아이디로 조회한다.")
    void 닉네임_변경_이력을_회원의_고유_아이디로_조회한다(){
        //given
        Member member = memberRepository.save(createBuilderMember());
        memberNicknameHistoryRepository.save(createBuilderMemberNicknameHistory(member));

        //when
        List<MemberNicknameHistory> actual = memberNicknameHistoryRepository.findAllByMemberId(member.getId());

        //then
        assertEquals(actual.size(), 1);
    }
}