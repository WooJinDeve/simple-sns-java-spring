package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.entity.TimeLine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeLineRepository extends JpaRepository<TimeLine, Long>, TimeLineCustomRepository {


    List<TimeLine> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    List<TimeLine> findAllByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId, Pageable pageable);


}
