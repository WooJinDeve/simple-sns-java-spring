package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    default Post getById(Long id){
        return findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    List<Post> findByMemberId(Long memberId);

    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    Long countByMemberId(Long memberId);

    List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    List<Post> findAllByMemberIdInOrderByIdDesc(List<Long> memberIds, Pageable pageable);

    List<Post> findAllByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId, Pageable pageable);

    List<Post> findAllByIdLessThanAndMemberIdInOrderByIdDesc(Long id, List<Long> memberIds, Pageable pageable);

    List<Post> findAllByIdIn(List<Long> ids);
}
