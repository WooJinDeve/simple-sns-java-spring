package com.sns.sns.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sns.sns.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Post {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    private String contents;

    private LocalDate createdDate;

    private LocalDateTime createdAt;

    @Builder
    public Post(Long id, Member member, String contents, LocalDate createdDate, LocalDateTime createdAt) {
        this.id = id;
        this.member = member.validateId();
        this.contents = Objects.requireNonNull(contents);
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ?LocalDateTime.now() : createdAt;
     }
}
