package com.sns.sns.domain.post.service;


import com.sns.sns.domain.post.entity.TimeLine;
import com.sns.sns.domain.post.repository.TimeLineRepository;
import com.sns.sns.util.CursorRequest;
import com.sns.sns.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimelineReadService {

    private final TimeLineRepository timeLineRepository;

    public PageCursor<TimeLine> getTimeLines(Long memberId, CursorRequest cursorRequest){
        var timelines =  findAllBy(memberId, cursorRequest);
        var nextKey = timelines.stream()
                .mapToLong(TimeLine::getId)
                .min().orElse(CursorRequest.NONE_KEY);

        return new PageCursor<>(cursorRequest.next(nextKey), timelines);
    }

    private List<TimeLine> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timeLineRepository.findAllByIdLessThanAndMemberIdOrderByIdDesc(cursorRequest.key(), memberId, PageRequest.of(0,cursorRequest.size()));
        }
        return timeLineRepository.findAllByMemberIdOrderByIdDesc(memberId, PageRequest.of(0, cursorRequest.size()));
    }


}
