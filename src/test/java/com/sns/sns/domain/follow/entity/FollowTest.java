package com.sns.sns.domain.follow.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sns.sns.common.FollowFixtures.createFollow;
import static com.sns.sns.common.MemberFixtures.createMember;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FollowTest {

    @Test
    @DisplayName("팔로우 객체를 생성한다.")
    void 팔로우_객체를_생성한다(){
        //given & when & then
        assertDoesNotThrow(() -> createFollow());
    }

    @Test
    @DisplayName("팔로우 요청 아이디나 대상 아이디가 null일 경우 오류를 던진다")
    void 팔로우_요청_아이디나_대상_아이디가_null일_경우_오류를_던진다(){
        //given & when & then
        Assertions.assertThatThrownBy(() -> createFollow(null, createMember()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
