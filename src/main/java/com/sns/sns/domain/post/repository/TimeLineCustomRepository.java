package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.entity.TimeLine;

import java.util.List;

public interface TimeLineCustomRepository {

    void bulkInsert(List<TimeLine> timeLines);
}
