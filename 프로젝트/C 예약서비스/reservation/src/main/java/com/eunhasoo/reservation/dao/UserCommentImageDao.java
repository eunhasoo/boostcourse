package com.eunhasoo.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.UserCommentImage;

@Repository
public class UserCommentImageDao {

	private final String SELECT_BY_ID = "select r.id as ImageId, r.reservation_info_id as reservationInfoId, r.reservation_user_comment_id as reservationUserCommentId, "
			+ "f.content_type, f.save_file_name, f.delete_flag, f.id as fileId, f.file_name, f.create_date, f.modify_date "
			+ "from reservation_user_comment_image r " + "inner join file_info f on r.file_id = f.id "
			+ "inner join reservation_info i on r.id = i.id " + "where r.reservation_user_comment_id = :userCommentId "
			+ "LIMIT 1";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public UserCommentImageDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public UserCommentImage selectUserCommentImage(int userCommentId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userCommentId", userCommentId);
		try {
			return jdbcTemplate.queryForObject(SELECT_BY_ID, param, (rs, rowNum) -> {
				return new UserCommentImage(rs.getInt("fileId"), rs.getInt("imageId"), rs.getInt("reservationInfoId"),
						rs.getInt("reservationUserCommentId"), rs.getBoolean("delete_flag"),
						rs.getString("content_type"), rs.getString("save_file_name"),
						rs.getTimestamp("modify_date").toLocalDateTime().toString(),
						rs.getTimestamp("create_date").toLocalDateTime().toString());
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
