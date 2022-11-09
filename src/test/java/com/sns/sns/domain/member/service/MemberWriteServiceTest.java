package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.entity.MemberNicknameHistory;
import com.sns.sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.sns.sns.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sns.sns.common.MemberFixtures.createMemberCommand;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberWriteServiceTest {
    @Autowired
    private MemberWriteService memberWriteService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("회원정보를 정상적으로 저장한다")
    void 회원정보를_정상적으로_저장한다() {
        //given & when
        Member actual = memberWriteService.create(createMemberCommand());

        //then
        assertThat(actual.getId()).isNotNull();
    }

    @Test
    @DisplayName("회원 닉네임 수정 한다.")
    void 회원_닉네임_수정한다() {
        //given
        Long id = memberWriteService.create(createMemberCommand()).getId();
        String expected = "닉네임수정";

        //when
        memberWriteService.changeNickname(id, expected);
        String actual = memberRepository.findById(id).get().getNickname();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("회원 저장시 MemberNameHistory에도 값이 저장된다.")
    void 회원_저장시_MemberNameHistory에도_같이_저장된다(){
        //given
        Long id = memberWriteService.create(createMemberCommand()).getId();

        //when
        List<MemberNicknameHistory> actual = memberNicknameHistoryRepository.findAllByMemberId(id);

        //then
        assertThat(actual.size()).isEqualTo(1);
    }
}
