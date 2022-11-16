package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.entity.TimeLine;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimeLineCustomRepositoryImpl implements TimeLineCustomRepository{

    private static final String TABLE = "Timeline";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void bulkInsert(List<TimeLine> timeLines) {
        var sql = String.format("""
                INSERT INTO `%s` (member_id, post_id,  created_at)
                VALUES (:member.id, :post.id, :createdAt)
                """, TABLE);

        SqlParameterSource[] params = timeLines.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
