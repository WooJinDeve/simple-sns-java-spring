package com.sns.sns.domain.member.entity;

import com.sns.sns.common.MemberNickNameHistoryFixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MemberNicknameHistoryTest {

    @Test
    @DisplayName("엔티티가 정삭적으로 생성된다.")
    void 엔티티가_정상적으로_생성된다() {
        assertDoesNotThrow(() -> MemberNickNameHistoryFixtures.createMember());
    }

    @Test
    @DisplayName("변경 닉네임에 null이 있을경우 생성시 오류를 던진다.")
    void 변경_닉네임에_null이_있을경우_생성시_오류를_던진다() {
        Assertions.assertThatThrownBy(() -> {
            MemberNicknameHistory.builder()
                    .memberId(1L)
                    .nickname(null)
                    .build();
        }).isInstanceOf(NullPointerException.class);
    }
}