package com.sns.sns.application.usecase;

import com.sns.sns.domain.follow.service.FollowReadService;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.service.PostReadService;
import com.sns.sns.domain.post.service.TimelineReadService;
import com.sns.sns.util.CursorRequest;
import com.sns.sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetTimeLinePostsUsecase {


    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    private final TimelineReadService timelineReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream()
                .map(follow -> follow.getToMember().getId())
                .toList();

        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeTimeline(Long memberId, CursorRequest cursorRequest) {
        var pagedTimelines = timelineReadService.getTimeLines(memberId, cursorRequest);
        var postIds = pagedTimelines.request().stream()
                .map(timeLine -> timeLine.getPost().getId())
                .toList();

        var posts = postReadService.getPosts(postIds);
        return new PageCursor<>(pagedTimelines.nextCursorRequest(), posts);
    }
}
