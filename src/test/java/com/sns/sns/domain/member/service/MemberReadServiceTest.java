package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.dto.MemberDto;
import com.sns.sns.domain.member.dto.MemberNicknameHistoryDto;
import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.sns.sns.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.sns.sns.common.MemberFixtures.createBuilderMember;
import static com.sns.sns.common.MemberNickNameHistoryFixtures.createBuilderMemberNicknameHistory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class MemberReadServiceTest {

    @Autowired
    private MemberReadService memberReadService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Test
    @DisplayName("회원을 단건 조회한다.")
    void 회원을_단건조회한다(){
        //given
        Long id = memberRepository.save(createBuilderMember()).getId();

        //when
        MemberDto actual = memberReadService.getMember(id);

        //then
        assertThat(actual.id()).isEqualTo(id);
    }

    @Test
    @DisplayName("존재하지 않는 회원 아이디로 조회시 오류를 던진다.")
    void 존재하지_않는_회원_아이디로_조회시_오류를_던진다(){
        //given & when & then
        assertThatThrownBy(() -> memberReadService.getMember(0L))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("회원 아이디를 통해 닉네임 변경 내역을 조회한다.")
    void 회원_아이디를_통해_닉네임_변경_내역을_조회한다(){
        //given
        Member member = memberRepository.save(createBuilderMember());
        memberNicknameHistoryRepository.save(createBuilderMemberNicknameHistory(member));

        //when
        MemberNicknameHistoryDto actual = memberReadService.getNicknameHistories(member.getId()).get(0);

        //then
        assertAll(() ->{
            assertThat(actual.memberId()).isEqualTo(member.getId());
            assertThat(actual.nickname()).isEqualTo(member.getNickname());
        });
    }
}