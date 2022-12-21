package com.sns.sns.domain.post.service;

import com.sns.sns.domain.member.dto.MemberDto;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.entity.PostLike;
import com.sns.sns.domain.post.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostLikeWriteService {

    private final PostLikeRepository postLikeRepository;

    public Long create(Post post, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();
        return postLikeRepository.save(postLike).getPostId();
    }

}
