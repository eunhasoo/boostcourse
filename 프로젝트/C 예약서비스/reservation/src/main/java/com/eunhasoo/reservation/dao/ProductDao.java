package com.eunhasoo.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.ProductListDto;


@Repository
public class ProductDao {
	
	private final String SELECT_ALL 
		  = "SELECT d.id, d.place_name, p.content, p.description, d.product_id, f.save_file_name " + 
			"FROM product p " + 
			"INNER JOIN display_info d ON p.id = d.product_id " + 
			"INNER JOIN product_image pi ON p.id=pi.product_id " + 
			"INNER JOIN file_info f ON pi.file_id=f.id " + 
			"WHERE pi.type=\"th\" " + 
			"ORDER BY d.product_id " + 
			"LIMIT :limit OFFSET :start";
	private final String SELECT_BY_ID
		  = "SELECT d.id, d.place_name, p.content, p.description, d.product_id, f.save_file_name " + 
			"FROM product p " + 
			"INNER JOIN display_info d ON p.id = d.product_id " + 
			"INNER JOIN product_image pi ON p.id=pi.product_id " + 
			"INNER JOIN file_info f ON pi.file_id=f.id " + 
			"WHERE pi.type=\"th\" AND p.category_id = :categoryId " + 
			"ORDER BY d.product_id " + 
			"LIMIT :limit OFFSET :start";
	private final String SELECT_COUNT_ALL = "select count(*) from product p "
			+ "inner join display_info d on p.id = d.product_id";
	private final String SELECT_COUNT_BY_ID = "select count(*) from product p "
			+ "inner join display_info d on p.id=d.product_id where p.category_id = :categoryId";

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductListDto> selectAll(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL, params, (rs, rowNum) -> {
			return new ProductListDto(rs.getInt("id"), rs.getInt("product_id"), rs.getString("description"),
					rs.getString("place_name"), rs.getString("content"), rs.getString("save_file_name"));
		});
	}
	
	public List<ProductListDto> selectById(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_BY_ID, params, (rs, rowNum) -> {
			return new ProductListDto(rs.getInt("id"), rs.getInt("product_id"), rs.getString("description"),
					rs.getString("place_name"), rs.getString("content"), rs.getString("save_file_name"));
		});
	}
	
	public int selectCountAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}
	
	public int selectCountById(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_BY_ID, params, Integer.class);
	}
}
