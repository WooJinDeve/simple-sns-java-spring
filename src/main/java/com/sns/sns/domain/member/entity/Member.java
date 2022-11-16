package com.sns.sns.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member {
    private final static Long NAME_MAX_LENGTH = 10L;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._-]+@[a-z]+[.]+[a-z]{2,3}$");

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, length = 10)
    private String nickname;

    @Column(unique = true)
    private String email;

    private LocalDate birthday;

    private LocalDateTime createdAt;

    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createdAt) {
        validateNickname(nickname);
        validateEmail(email);

        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public void changeNickname(final String other) {
        Objects.requireNonNull(other);
        validateNickname(other);
        nickname = other;
    }

    private void validateEmail(final String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        Assert.isTrue(matcher.matches(), "이메일 형식이 맞지 않습니다.");
    }

    private void validateNickname(final String nickname){
        Assert.isTrue(nickname.length() <= NAME_MAX_LENGTH, "최대 길이를 초과했습니다.");
    }
}
