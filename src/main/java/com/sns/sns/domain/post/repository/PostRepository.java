package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.dto.DailyPostCount;
import com.sns.sns.domain.post.dto.DailyPostCountRequest;
import com.sns.sns.domain.post.entity.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostRepository {
    private static final String TABLE = "Post";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final RowMapper<Post> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Post.builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .contents(resultSet.getString("contents"))
            .createdDate(resultSet.getObject("createdDate", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();


    public PostRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
    }


    public Post save(Post post) {
        if (post.getId() == null)
            return insert(post);
        throw new UnsupportedOperationException("Post는 갱신을 지원하지 않습니다");
    }

    private Post insert(Post post) {
        var params = new BeanPropertySqlParameterSource(post);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .contents(post.getContents())
                .createdDate(post.getCreatedDate())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public List<Post> findByMemberId(Long memberId) {
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId);
        var sql = String.format("SELECT * FROM `%s` WHERE id = :id", TABLE);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request){
        var sql = String.format("""
                SELECT memberId, createdDate, count(id) as cnt 
                FROM %s 
                WHERE memberId = :memberId and createdDate between :firstDate and :lastDate 
                GROUP BY memberId, createdDate
                """, TABLE);
        var params = new BeanPropertySqlParameterSource(request);

        RowMapper<DailyPostCount> mapper = (ResultSet resultSet, int rowNum) -> new DailyPostCount(
                resultSet.getLong("memberId"),
                resultSet.getObject("createdDate", LocalDate.class),
                resultSet.getLong("cnt")
        );
        return namedParameterJdbcTemplate.query(sql, params, mapper);
    }
}
