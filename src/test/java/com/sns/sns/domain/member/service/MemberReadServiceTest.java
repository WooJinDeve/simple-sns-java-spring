package com.sns.sns.domain.member.service;

import com.sns.sns.domain.member.dto.MemberDto;
import com.sns.sns.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.sns.sns.common.MemberFixtures.createBuilderMember;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberReadServiceTest {

    @Autowired
    private MemberReadService memberReadService;
    @Autowired
    private MemberRepository memberRepository;

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
}