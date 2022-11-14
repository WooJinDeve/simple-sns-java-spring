package com.sns.sns.application.controller;

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

    @PostMapping
    public Long create(@RequestBody  PostCommand command) {
        return postWriteService.create(command);
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
}
