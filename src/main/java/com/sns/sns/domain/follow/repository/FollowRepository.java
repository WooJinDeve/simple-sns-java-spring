package com.sns.sns.domain.follow.repository;

import com.sns.sns.domain.follow.entity.Follow;
import com.sns.sns.domain.member.entity.Member;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFromMemberId(Long fromMemberId);

    List<Follow> findAllByToMemberId(Long toMemberId);

    boolean existsByFromMemberAndToMember(Member fromMember, Member toMember);

    default void validateExistByFromMemberAndToMember(Member fromMember, Member toMember){
        if (existsByFromMemberAndToMember(fromMember,toMember))
            throw new DuplicateRequestException();
    }
}
