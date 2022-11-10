package com.sns.sns.domain.post.service;

import com.sns.sns.domain.post.dto.DailyPostCount;
import com.sns.sns.domain.post.dto.DailyPostCountRequest;
import com.sns.sns.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }
}
