package com.eunhasoo.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.UserComment;

@Repository
public class UserCommentDao {

	private final String SELECT_BY_ID = "select r.id as commentId, comment, r.create_date, r.modify_date, r.product_id, r.score, "
			+ "i.reservation_date, i.reservation_date, i.reservation_email, "
			+ "i.id as reservationInfoId, i.reservation_name, i.reservation_tel as reservationTelephone "
			+ "from reservation_user_comment r " + "inner join reservation_info i on r.reservation_info_id = i.id "
			+ "where i.display_info_id = :displayInfoId";

	private final String SELECT_SCORE = "select avg(score) as averageScore " + "from reservation_user_comment c "
			+ "inner join reservation_info i on c.reservation_info_id = i.id "
			+ "where i.display_info_id = :displayInfoId";

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

	public double selectAvgScore(int displayInfoId) {
		Map<String, Object> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_SCORE, param, Double.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

}
