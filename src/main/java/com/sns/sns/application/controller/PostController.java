package com.sns.sns.application.controller;

import com.sns.sns.application.usecase.CreatePostLikeUsecase;
import com.sns.sns.application.usecase.CreatePostUsecase;
import com.sns.sns.application.usecase.GetTimeLinePostsUsecase;
import com.sns.sns.domain.post.dto.DailyPostCount;
import com.sns.sns.domain.post.dto.DailyPostCountRequest;
import com.sns.sns.domain.post.dto.PostCommand;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.service.PostReadService;
import com.sns.sns.domain.post.service.PostWriteService;
import com.sns.sns.util.CursorRequest;
import com.sns.sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private final GetTimeLinePostsUsecase getTimeLinePostsUsecase;
    private final CreatePostUsecase createPostUsecase;

    private final CreatePostLikeUsecase createPostLikeUsecase;


    @PostMapping
    public Long create(@RequestBody  PostCommand command) {
        return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("/members/{id}")
    public Page<Post> getPosts(
            @PathVariable(name = "id") Long memberId,
            Pageable pageable
    ){

        return postReadService.getPosts(memberId, pageable);
    }
    @GetMapping("/members/{id}/by-cursor")
    public PageCursor<Post> getPosts(
            @PathVariable(name = "id") Long memberId,
            CursorRequest cursorRequest
    ){

        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/members/{id}/timeline")
    public PageCursor<Post> getTimeLine(
            @PathVariable(name = "id") Long memberId,
            CursorRequest cursorRequest
    ){

        return getTimeLinePostsUsecase.executeTimeline(memberId, cursorRequest);
    }


    @PostMapping("/{postId}/like/v1")
    public void likePost(Long postId){
//        postWriteService.likePost(postId);
        postWriteService.likePostByOptimisticLock(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void likePostV2(@PathVariable Long postId, @RequestParam Long memberId){
        createPostLikeUsecase.execute(postId, memberId);
    }
}
