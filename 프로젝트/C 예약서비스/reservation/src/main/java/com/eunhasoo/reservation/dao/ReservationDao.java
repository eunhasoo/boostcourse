package com.eunhasoo.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.eunhasoo.reservation.dto.ReservationDto;
import com.eunhasoo.reservation.dto.ReservationPrice;

@Repository
public class ReservationDao {

	private final String INSERT_RESERVATION =
			"insert into reservation_info (product_id, display_info_id, reservation_name, reservation_tel, "
			+ "reservation_email, reservation_date, cancel_flag, create_date, modify_date) "
			+ "values (:productId, :displayInfoId, :reservationName, :reservationTel, "
			+ ":reservationEmail, :reservationDate, :cancelFlag, :createDate, :modifyDate)";
	
	private final String INSERT_RESERVATION_INFO_PRICE = 
			"insert into reservation_info_price (reservation_info_id, product_price_id, count) "
			+ "values (:reservationInfoId, :productPriceId, :count)";
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ReservationDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public Integer saveReservation(ReservationDto dto) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("productId", dto.getProductId())
									   .addValue("displayInfoId", dto.getDisplayInfoId())
									   .addValue("reservationName", dto.getReservationName())
									   .addValue("reservationEmail", dto.getReservationEmail())
									   .addValue("reservationTel", dto.getReservationTel())
									   .addValue("cancelFlag", dto.isCancelFlag())
									   .addValue("reservationDate", dto.getReservationDate())
									   .addValue("createDate", dto.getCreateDate())
									   .addValue("modifyDate", dto.getModifyDate());
		jdbcTemplate.update(INSERT_RESERVATION, parameters, keyHolder);
		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
	}
	
	public void saveReservationPrice(Integer reservationInfoId, List<ReservationPrice> prices) {
		SqlParameterSource parameters;
		ReservationPrice price;
		for (int i = 0; i < prices.size(); i++) {
			price = prices.get(i);
			parameters = new MapSqlParameterSource().addValue("productPriceId", price.getProductPriceId())
								.addValue("reservationInfoId", reservationInfoId)
								.addValue("count", price.getCount());
			jdbcTemplate.update(INSERT_RESERVATION_INFO_PRICE, parameters);
		}
	}
	
}
