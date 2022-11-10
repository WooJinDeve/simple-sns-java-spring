package com.sns.sns.common;

import com.sns.sns.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtures {

    public static EasyRandom get(Long memberId, LocalDate fistDate, LocalDate lastDate) {
        var idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        var param = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(fistDate, lastDate)
                .randomize(memberIdPredicate, () -> memberId);
        return new EasyRandom(param);
    }
}
