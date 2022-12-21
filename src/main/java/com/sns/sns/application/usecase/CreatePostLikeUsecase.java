package com.sns.sns.application.usecase;

import com.sns.sns.domain.member.service.MemberReadService;
import com.sns.sns.domain.post.service.PostLikeWriteService;
import com.sns.sns.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostLikeUsecase {

    private final PostReadService postReadService;
    private final MemberReadService memberReadService;
    private final PostLikeWriteService postLikeWriteService;

    public Long execute(Long postId, Long memberId){
        var posts = postReadService.getPost(postId);
        var member = memberReadService.getMember(memberId);
        return postLikeWriteService.create(posts, member);
    }
}
