package com.eunhasoo.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {

	private final String SELECT_BY_ID = "select f.content_type, f.create_date, f.delete_flag, d.display_info_id as displayInfoId, "
			+ "d.id as displayInfoImageId, f.id as fileId, f.file_name, f.modify_date, f.save_file_name "
			+ "from display_info_image d " + "inner join file_info f on d.file_id = f.id "
			+ "where d.id = :displayInfoId";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		Map<String, Object> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_BY_ID, param, (rs, rowNum) -> {
				return new DisplayInfoImage(rs.getInt("displayInfoId"), rs.getInt("displayInfoImageId"),
						rs.getInt("fileId"), rs.getBoolean("delete_flag"), rs.getString("content_type"),
						rs.getString("file_name"), rs.getString("save_file_name"),
						rs.getTimestamp("create_date").toLocalDateTime().toString(),
						rs.getTimestamp("modify_date").toLocalDateTime().toString());
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
