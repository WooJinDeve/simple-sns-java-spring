package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.dto.DailyPostCount;
import com.sns.sns.domain.post.dto.DailyPostCountRequest;
import com.sns.sns.domain.post.entity.Post;

import java.util.List;

public interface PostCustomRepository {

    List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request);

    void bulkInsert(List<Post> posts);
}
