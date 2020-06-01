package com.eunhasoo.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.ProductPrice;

@Repository
public class ProductPriceDao {

	private final String SELECT_BY_ID = "select p.id as productPriceId, p.product_id as productId, p.create_date, price, "
			+ "price_type_name, p.modify_date, discount_rate " + "from product_price p "
			+ "inner join display_info d on p.product_id = d.product_id " + "where d.id = :displayInfoId";

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public ProductPriceDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductPrice> selectProductPrice(int displayInfoId) {
		Map<String, Object> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		try {
			return jdbcTemplate.query(SELECT_BY_ID, param, (rs, rowNum) -> {
				return new ProductPrice(rs.getInt("discount_rate"), rs.getInt("price"), rs.getInt("productId"),
						rs.getInt("productPriceId"), rs.getString("price_type_name"),
						rs.getTimestamp("modify_date").toLocalDateTime().toString(),
						rs.getTimestamp("create_date").toLocalDateTime().toString());
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
