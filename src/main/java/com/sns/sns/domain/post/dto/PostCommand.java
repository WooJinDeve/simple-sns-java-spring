package com.sns.sns.domain.post.dto;

public record PostCommand(Long memberId, String contents) {
}
