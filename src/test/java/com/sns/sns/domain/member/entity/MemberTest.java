package com.sns.sns.domain.member.entity;

import com.sns.sns.common.MemberFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.sns.sns.common.MemberFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MemberTest {

    @Test
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    void 회원은_닉네임을_변경할_수_있다() {
        //given
        Member member = createMember();
        String expected = "woo";

        //when
        member.changeNickname(expected);

        //then
        assertThat(member.getNickname()).isEqualTo(expected);
    }

    @Test
    @DisplayName("닉네임 변경시 변경 닉네임이 null이면 오류를 반환한다.")
    void 닉네임_변경시_변경_닉네임이_null이면_오류를_반환한다() {
        //given
        Member member = createMember();
        String expected = null;

        //when
        assertThatThrownBy(() -> member.changeNickname(expected))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("회원을 정상적으로 생성한다")
    void 회원을_정상적으로_생성한다() {
        //given & when & then
        assertDoesNotThrow(() -> MemberFixtures.createBuilderMember());
    }

    @ParameterizedTest
    @DisplayName("닉네임의 길이가 10이상일 경우 오류를 던진다.")
    @ValueSource(strings = {"가나다라마바사아자차카타파하"})
    void 닉네임의_길이가_10이상일_경우_오류를_던진다(final String nickname) {
        //given & when & then
        assertThatThrownBy(() -> {
            Member.builder()
                    .email(이메일)
                    .nickname(nickname)
                    .birthday(생일)
                    .build();
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 길이를 초과했습니다.");
    }

    @ParameterizedTest
    @DisplayName("이메일 형식이 맞지 않는 경우 오류를 던진다.")
    @ValueSource(strings = {"default@", "default.email.com", "default.email","default@email", "@email.com"})
    void 이메일_형식이_맞지_않는_경우_오류를_던진다(String email){
        //given & when & then
        assertThatThrownBy(() -> {
            Member.builder()
                    .email(email)
                    .nickname(닉네임)
                    .birthday(생일)
                    .build();
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이메일 형식이 맞지 않습니다.");
    }
}
