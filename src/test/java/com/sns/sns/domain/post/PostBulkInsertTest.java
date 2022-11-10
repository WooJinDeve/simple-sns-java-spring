package com.sns.sns.domain.post;

import com.sns.sns.common.PostFixtures;
import com.sns.sns.domain.post.entity.Post;
import com.sns.sns.domain.post.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        var easyRandom = PostFixtures.get(
                4L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2022, 2, 1)
        );
        var stopWatch = new StopWatch();
        stopWatch.start();

        List<Post> post = IntStream.range(0, 10000 * 100)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .collect(Collectors.toList());
        
        stopWatch.stop();
        System.out.println("stopWatch.getTotalTimeSeconds() = " + stopWatch.getTotalTimeSeconds());


        postRepository.bulkInsert(post);
    }

}
