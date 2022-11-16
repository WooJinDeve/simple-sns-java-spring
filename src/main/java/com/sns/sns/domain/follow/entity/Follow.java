package com.sns.sns.domain.follow.entity;

import com.sns.sns.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Follow {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "to_member_id")
    private Member toMember;
    private LocalDateTime createdAt;

    @Builder
    public Follow(Long id, Member fromMember, Member toMember, LocalDateTime createdAt) {
        this.id = id;
        this.fromMember = fromMember.validateId();
        this.toMember = toMember.validateId();
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}