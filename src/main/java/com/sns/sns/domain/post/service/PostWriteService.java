package com.sns.sns.domain.post.service;

import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.post.dto.PostCommand;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class PostWriteService {

    private final PostRepository postRepository;

    public Long create(PostCommand command, Member member) {
        var post = Post.builder()
                .member(member)
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }
}
