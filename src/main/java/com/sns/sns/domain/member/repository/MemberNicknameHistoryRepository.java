package com.sns.sns.domain.member.repository;

import com.sns.sns.domain.member.entity.MemberNicknameHistory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MemberNicknameHistoryRepository {
    private static final String TABLE = "MemberNicknameHistory";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final RowMapper<MemberNicknameHistory> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> MemberNicknameHistory
            .builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .nickname(resultSet.getString("nickname"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public MemberNicknameHistoryRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(this.namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
    }

    public MemberNicknameHistory save(MemberNicknameHistory history){
        if (history.getId() == null)
            return insert(history);

        throw new UnsupportedOperationException();
    }

    private MemberNicknameHistory insert(MemberNicknameHistory history){
        var params = new BeanPropertySqlParameterSource(history);
        Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return MemberNicknameHistory.builder()
                .id(id)
                .memberId(history.getMemberId())
                .nickname(history.getNickname())
                .createdAt(history.getCreatedAt())
                .build();
    }

    public List<MemberNicknameHistory> findAllByMemberId(Long memberId){
        var sql = String.format("SELECT * FROM %s WHERE memberId = :memberId", TABLE);
        var params = new MapSqlParameterSource().addValue("memberId", memberId);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }
}
