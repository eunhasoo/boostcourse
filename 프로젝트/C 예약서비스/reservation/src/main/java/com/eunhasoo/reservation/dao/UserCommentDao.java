package com.eunhasoo.reservation.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.CommentRequestDto;
import com.eunhasoo.reservation.dto.UserComment;

@Repository
public class UserCommentDao {

	private final String SELECT_BY_ID 
	= "select r.id as commentId, comment, r.create_date, r.modify_date, r.product_id, r.score, "
		+ "i.reservation_date, i.reservation_date, i.reservation_email, "
		+ "i.id as reservationInfoId, i.reservation_name, i.reservation_tel as reservationTelephone "
		+ "from reservation_user_comment r " + "inner join reservation_info i on r.reservation_info_id = i.id "
		+ "where i.display_info_id = :displayInfoId";

	private final String SELECT_SCORE 
	= "select avg(score) as averageScore " + "from reservation_user_comment c "
		+ "inner join reservation_info i on c.reservation_info_id = i.id "
		+ "where i.display_info_id = :displayInfoId";

	private final String INSERT_COMMENT 
	= "insert into reservation_user_comment ( "
		+ "product_id, reservation_info_id, score, comment, create_date, modify_date) "
		+ "values ("
		+ ":productId, :reservationInfoId, :score, :comment, :createDate, :modifyDate)";
	
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public UserCommentDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<UserComment> selectUserComment(int displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.query(SELECT_BY_ID, params, (rs, rowNum) -> {
				return new UserComment(rs.getInt("commentId"), rs.getInt("product_id"), rs.getInt("reservationInfoId"),
						rs.getInt("score"), rs.getString("comment"), rs.getString("reservation_email"),
						rs.getString("reservation_name"), rs.getString("reservationTelephone"),
						rs.getTimestamp("reservation_date").toLocalDateTime().toString(),
						rs.getTimestamp("create_date").toLocalDateTime().toString(),
						rs.getTimestamp("modify_date").toLocalDateTime().toString());
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public Double selectAvgScore(int displayInfoId) {
		Map<String, Object> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_SCORE, param, Double.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Integer insertComment(CommentRequestDto commentRequestDto) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		SqlParameterSource parameters = new MapSqlParameterSource()
											.addValue("productId", commentRequestDto.getProductId())
											.addValue("reservationInfoId", commentRequestDto.getReservationInfoId())
											.addValue("comment", commentRequestDto.getComment())
											.addValue("score", commentRequestDto.getScore())
											.addValue("createDate", date)
											.addValue("modifyDate", date);
		jdbcTemplate.update(INSERT_COMMENT, parameters, keyHolder);
		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
	}

}
