package com.sns.sns.application.usecase;

import com.sns.sns.domain.follow.entity.Follow;
import com.sns.sns.domain.follow.service.FollowReadService;
import com.sns.sns.domain.member.repository.MemberRepository;
import com.sns.sns.domain.post.dto.PostCommand;
import com.sns.sns.domain.post.repository.PostRepository;
import com.sns.sns.domain.post.service.PostWriteService;
import com.sns.sns.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostUsecase {

    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Long execute(PostCommand postCommand){
        var member = memberRepository.getMember(postCommand.memberId());
        var post = postRepository.getById(postWriteService.create(postCommand, member));

        var followMembers = followReadService.getFollowers(postCommand.memberId()).stream()
                .map(Follow::getToMember)
                .toList();

        timelineWriteService.deliveryToTimeLine(post, followMembers);
        return post.getId();
    }
}
