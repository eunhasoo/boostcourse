package com.eunhasoo.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.PromotionDto;

@Repository
public class PromotionDao {

	private final String SELECT_ALL = "select p.id, p.product_id, f.file_name " + "from promotion p "
			+ "inner join product_image i on p.product_id = i.product_id "
			+ "inner join file_info f on i.file_id = f.id " + "where i.type = \"th\"";

	JdbcTemplate jdbcTemplate;

	@Autowired
	public PromotionDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<PromotionDto> selectAll() {
		return jdbcTemplate.query(SELECT_ALL, (rs, rowNum) -> {
			return new PromotionDto(rs.getInt("id"), rs.getInt("product_id"), rs.getString("file_name"));
		});
	}
	
}
