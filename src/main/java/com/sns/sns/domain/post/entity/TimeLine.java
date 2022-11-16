package com.sns.sns.domain.post.entity;


import com.sns.sns.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "timeline")
@NoArgsConstructor(access = PROTECTED)
public class TimeLine {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    private LocalDateTime createdAt;

    @Builder
    public TimeLine(Long id, Member member, Post post, LocalDateTime createdAt) {
        this.id = id;
        this.member = member.validateId();
        this.post = post;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

}
