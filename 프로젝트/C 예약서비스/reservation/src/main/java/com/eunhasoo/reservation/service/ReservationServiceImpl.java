package com.eunhasoo.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunhasoo.reservation.dao.ReservationDao;
import com.eunhasoo.reservation.dto.ReservationDto;
import com.eunhasoo.reservation.dto.ReservationPrice;
import com.eunhasoo.reservation.dto.ReservationRequestDto;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationDao reservationDao;
	
	@Autowired
	public ReservationServiceImpl(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}
	
	@Override
	@Transactional
	public Integer saveReservation(ReservationRequestDto reservationDto) {
		ReservationDto dto = reservationDto.toDTO();
		Integer reservationInfoId = reservationDao.saveReservation(dto);
		List<ReservationPrice> prices = reservationDto.getPrices();
		if (reservationInfoId != 0 & prices != null && prices.size() > 0)
			saveReservationPrice(reservationInfoId, prices);
		return 0;
	}

	@Override
	public void saveReservationPrice(Integer reservationInfoId, List<ReservationPrice> prices) {
		reservationDao.saveReservationPrice(reservationInfoId, prices);
	}
	
}
