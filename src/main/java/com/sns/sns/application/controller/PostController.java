package com.sns.sns.application.controller;

import com.sns.sns.domain.post.dto.PostCommand;
import com.sns.sns.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostWriteService postWriteService;


    @PostMapping
    public Long create(@RequestBody  PostCommand command) {
        return postWriteService.create(command);
    }
}
