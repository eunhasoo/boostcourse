package com.eunhasoo.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.ProductImage;

@Repository
public class ProductImageDao {

	private final String SELECT_BY_ID = "select p.product_id as productId, p.id as productImageId, p.type, f.id as fileInfoId, f.file_name, "
			+ "f.save_file_name, f.content_type, f.delete_Flag, f.create_date, f.modify_date " + "from product_image p "
			+ "inner join display_info d on p.product_id = d.product_id "
			+ "inner join file_info f on p.file_id = f.id " + "where p.type in (\"ma\", \"et\") and d.id = :displayInfoId "
			+ "LIMIT 2";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ProductImageDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductImage> selectProductImage(int displayInfoId) {
		Map<String, Object> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.query(SELECT_BY_ID, param, (rs, rowNum) -> {
				return new ProductImage(rs.getInt("productId"), rs.getInt("fileInfoId"), rs.getInt("productImageId"),
						rs.getBoolean("delete_Flag"), rs.getString("content_type"), rs.getString("file_name"),
						rs.getString("save_file_name"), rs.getString("type"),
						rs.getTimestamp("modify_date").toLocalDateTime().toString(),
						rs.getTimestamp("create_date").toLocalDateTime().toString());
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
