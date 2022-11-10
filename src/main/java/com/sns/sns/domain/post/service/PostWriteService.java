package com.sns.sns.domain.post.service;

import com.sns.sns.domain.post.dto.PostCommand;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }
}
