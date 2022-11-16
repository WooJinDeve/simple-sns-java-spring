package com.sns.sns.domain.post.service;

import com.sns.sns.domain.member.entity.Member;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.entity.TimeLine;
import com.sns.sns.domain.post.repository.TimeLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TimelineWriteService {

    private final TimeLineRepository timeLineRepository;


    public void deliveryToTimeLine(Post post, List<Member> members) {
        var timelines = members.stream()
                .map(member -> toTimeLine(post, member))
                .toList();

        timeLineRepository.bulkInsert(timelines);
    }

    private static TimeLine toTimeLine(Post post, Member member) {
        return TimeLine.builder().member(member).post(post).build();
    }
}
