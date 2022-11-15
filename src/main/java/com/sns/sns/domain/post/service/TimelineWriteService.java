package com.sns.sns.domain.post.service;

import com.sns.sns.domain.post.entity.TimeLine;
import com.sns.sns.domain.post.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineWriteService {

    private final TimeLineRepository timeLineRepository;


    public void deliveryToTimeLine(Long postId, List<Long> toMemberIds) {
        var timelines = toMemberIds.stream()
                .map(memberId -> toTimeLine(postId, memberId))
                .toList();

        timeLineRepository.bulkInsert(timelines);
    }

    private static TimeLine toTimeLine(Long postId, Long memberId) {
        return TimeLine.builder().memberId(memberId).postId(postId).build();
    }
}
