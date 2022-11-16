package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.dto.DailyPostCount;
import com.sns.sns.domain.post.dto.DailyPostCountRequest;
import com.sns.sns.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository{
    private static final String TABLE = "Post";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {
        var sql = String.format("""
                SELECT member_id, created_date, count(id) as cnt 
                FROM %s 
                WHERE member_id = :memberId and created_date between :firstDate and :lastDate 
                GROUP BY member_id, created_date
                """, TABLE);
        var params = new BeanPropertySqlParameterSource(request);

        RowMapper<DailyPostCount> mapper = (ResultSet resultSet, int rowNum) -> new DailyPostCount(
                resultSet.getLong("member_id"),
                resultSet.getObject("created_date", LocalDate.class),
                resultSet.getLong("cnt")
        );
        return namedParameterJdbcTemplate.query(sql, params, mapper);
    }

    @Override
    public void bulkInsert(List<Post> posts) {
        var sql = String.format("""
                INSERT INTO `%s` (member_id, contents, created_date, created_at)
                VALUES (:member.id, :contents, :createdDate, :createdAt)
                """, TABLE);

        SqlParameterSource[] params = posts.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
