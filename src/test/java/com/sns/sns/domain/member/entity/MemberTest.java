package com.sns.sns.domain.member.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.sns.sns.common.MemberFixtures.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MemberTest {

    @Test
    @DisplayName("회원을 정상적으로 생성한다")
    void 회원을_정상적으로_생성한다() {
        //given & when & then
        assertDoesNotThrow(() -> {
            Member.builder()
                    .email(이메일)
                    .nickname(닉네임)
                    .birthday(생일)
                    .build();
        });
    }

    @ParameterizedTest
    @DisplayName("닉네임의 길이가 10이상일 경우 오류를 던진다.")
    @ValueSource(strings = {"가나다라마바사아자차카타파하"})
    void 닉네임의_길이가_10이상일_경우_오류를_던진다(final String nickname) {
        //given & when & then
        Assertions.assertThatThrownBy(() -> {
                    Member.builder()
                            .email(이메일)
                            .nickname(nickname)
                            .birthday(생일)
                            .build();
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 길이를 초과했습니다.");
    }
}
