package com.eunhasoo.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.CategoryListDto;

@Repository
public class CategoryDao {

	private final String SELECT_ALL 
		= "select c.id, c.name, count(*) as count from display_info d "
		+ "inner join product p on d.product_id = p.id " 
		+ "inner join category c on p.category_id = c.id "
		+ "group by c.id, c.name";

	private JdbcTemplate jdbc;

	@Autowired
	public CategoryDao(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	public List<CategoryListDto> selectAll() {
		return jdbc.query(SELECT_ALL, (rs, rowNum) -> {
			CategoryListDto dto = new CategoryListDto();
			dto.setId(rs.getInt(1));
			dto.setName(rs.getString(2));
			dto.setCount(rs.getInt(3));
			return dto;
		});
	}
}
