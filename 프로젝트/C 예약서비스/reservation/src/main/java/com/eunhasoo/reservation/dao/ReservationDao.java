package com.eunhasoo.reservation.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	private final String SELECT_RESERVATION_INFO_BY_EMAIL = 
			"select id, product_id, display_info_id, reservation_name, reservation_tel, reservation_email, "
			+ "reservation_date, cancel_flag, create_date, modify_date "
			+ "from reservation_info "
			+ "where reservation_email = :email "
			+ "order by id desc";
	
	private final String SELECT_TOTAL_PRICE = 
			"select sum(count * price) as total_price " 
			+ "from reservation_info_price r, product_price p "
			+ "where r.product_price_id = p.id "
			+ "and reservation_info_id = :reservationInfoId " 
			+ "group by reservation_info_id";
	
	private final String UPDATE_CANCEL_FLAG = 
			"update reservation_info "
			+ "set cancel_flag = :flag, "
			+ "modify_date = :modifyDate "
			+ "where id = :reservationInfoId";
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public ReservationDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public Integer insertReservation(ReservationDto dto) {
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
	
	public void insertReservationPrice(Integer reservationInfoId, List<ReservationPrice> prices) {
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
	
	public List<ReservationDto> selectReservationInfoByEmail(String email) {
		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		try {
			return jdbcTemplate.query(SELECT_RESERVATION_INFO_BY_EMAIL, param, (rs, rowNum) -> {
				return new ReservationDto(
						rs.getInt("display_info_id"),
						rs.getInt("product_id"),
						rs.getString("reservation_email"),
						rs.getString("reservation_name"),
						rs.getTimestamp("reservation_date").toLocalDateTime().toString(), 
						rs.getString("reservation_tel"),
						rs.getInt("id"),
						rs.getBoolean("cancel_flag"),
						rs.getTimestamp("create_date").toLocalDateTime().toString(),
						rs.getTimestamp("modify_date").toLocalDateTime().toString());
			});
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<>();
		}	
	}
	
	public Integer selectTotalPrice(Integer reservationInfoId) {
		Map<String, Object> param = new HashMap<>();
		param.put("reservationInfoId", reservationInfoId);
		try {
			return jdbcTemplate.queryForObject(SELECT_TOTAL_PRICE, param, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public void updateReservation(Integer reservationId) {
		Map<String, Object> params = new HashMap<>();
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		params.put("flag", true);
		params.put("modifyDate", timestamp);
		params.put("reservationInfoId", reservationId);
		jdbcTemplate.update(UPDATE_CANCEL_FLAG, params);
	}
}
