package com.sns.sns.domain.post.repository;

import com.sns.sns.domain.post.entity.TimeLine;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TimeLineRepository {

    private static final String TABLE = "Timeline";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    private static final RowMapper<TimeLine> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> TimeLine.builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .postId(resultSet.getLong("postId"))
            .createdAt(resultSet.getObject("createAt", LocalDateTime.class))
            .build();

    public TimeLineRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
    }

    public TimeLine save(TimeLine timeLine){
        if (timeLine.getId() == null)
            return insert(timeLine);

        throw new UnsupportedOperationException("TimeLine은 갱신을 지원하지 않습니다.");
    }

    private TimeLine insert(TimeLine timeLine) {
        var params = new BeanPropertySqlParameterSource(timeLine);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return TimeLine.builder()
                .id(id)
                .memberId(timeLine.getMemberId())
                .postId(timeLine.getPostId())
                .createdAt(timeLine.getCreatedAt())
                .build();
    }

    public List<TimeLine> findAllByMemberIdOrderByIdDesc(Long memberId, int size){
        var params = new MapSqlParameterSource()
                .addValue("memberId",memberId)
                .addValue("size", size);

        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE memberId = :memberId
                ORDER BY id DESC
                LIMIT :size
                """, TABLE);

        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public List<TimeLine> findAllByLessThanIdMemberIdAndOrderByIdDesc(Long id, Long memberId, int size){
        var params = new MapSqlParameterSource()
                .addValue("memberId",memberId)
                .addValue("id", id)
                .addValue("size", size);

        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE memberId = :memberId and id < :id
                ORDER BY id DESC
                LIMIT :size
                """, TABLE);

        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }

    public void bulkInsert(List<TimeLine> timeLines) {
        var sql = String.format("""
                INSERT INTO `%s` (memberId, postId,  createAt)
                VALUES (:memberId, :postId, :createdAt)
                """, TABLE);

        SqlParameterSource[] params = timeLines.stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
