package com.sns.sns.application.usecase;

import com.sns.sns.domain.follow.entity.Follow;
import com.sns.sns.domain.follow.service.FollowReadService;
import com.sns.sns.domain.post.dto.PostCommand;
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

    public Long execute(PostCommand postCommand){
        var postId = postWriteService.create(postCommand);

        var followMemberIds = followReadService.getFollowers(postCommand.memberId()).stream()
                .map(Follow::getFromMemberId)
                .toList();

        timelineWriteService.deliveryToTimeLine(postId, followMemberIds);

        return postId;
    }
}
