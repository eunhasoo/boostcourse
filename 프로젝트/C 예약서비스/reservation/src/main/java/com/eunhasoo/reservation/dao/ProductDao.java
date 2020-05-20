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
	
	private final String SELECT_ALL = "";
	private final String SELECT_BY_ID = "";
	private final String SELECT_COUNT_ALL = "select count(*) from product p inner join display_info d on p.id = d.product_id";
	private final String SELECT_COUNT_BY_ID = "select count(*) from product p inner join display_info d on p.id=d.product_id where p.category_id = :categoryId";

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductListDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductListDto.class);
	
	@Autowired
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductListDto> selectAll(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
	
	public List<ProductListDto> selectById(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		return null;
	}
	
	public Integer selectCountAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}
	
	public Integer selectCountById(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_COUNT_BY_ID, params, Integer.class);
	}
}
