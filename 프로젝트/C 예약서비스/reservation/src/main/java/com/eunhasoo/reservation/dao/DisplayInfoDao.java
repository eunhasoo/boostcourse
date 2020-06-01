package com.eunhasoo.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.DisplayInfo;

@Repository
public class DisplayInfoDao {

	private final String SELECT_BY_ID = "select c.id as categoryId, c.name as categoryName, d.create_date, d.id as displayInfoId, "
			+ "d.email, homepage, d.modify_date as modifyDate, opening_hours as openingHours, place_lot as placeLot, "
			+ "place_name as placeName, place_street as placeStreet, p.content, p.description, p.event, p.id as productId, d.tel as telephone "
			+ "from display_info d " + "inner join product p on d.product_id = p.id "
			+ "inner join category c on p.category_id = c.id " + "where d.id = :displayInfoId";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_BY_ID, params, (rs, rowNum) -> {
				return new DisplayInfo(rs.getInt("categoryId"), rs.getString("categoryName"),
						rs.getTimestamp("create_date").toLocalDateTime().toString(), rs.getInt("displayInfoId"),
						rs.getString("email"), rs.getString("homepage"),
						rs.getTimestamp("modifyDate").toLocalDateTime().toString(), rs.getString("openingHours"),
						rs.getString("placeLot"), rs.getString("placeName"), rs.getString("placeStreet"),
						rs.getString("content"), rs.getString("description"), rs.getString("event"),
						rs.getInt("productId"), rs.getString("telephone"));
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
