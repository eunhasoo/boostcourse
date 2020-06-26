package com.eunhasoo.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunhasoo.reservation.dao.DisplayInfoDao;
import com.eunhasoo.reservation.dao.ReservationDao;
import com.eunhasoo.reservation.dto.ReservationDto;
import com.eunhasoo.reservation.dto.ReservationPrice;
import com.eunhasoo.reservation.dto.ReservationRequestDto;
import com.eunhasoo.reservation.dto.ReservationResponseDto;

@Service
public class ReservationServiceImpl implements ReservationService {

	private final ReservationDao reservationDao;
	private final DisplayInfoDao displayInfoDao;
	
	@Autowired
	public ReservationServiceImpl(ReservationDao reservationDao, DisplayInfoDao displayInfoDao) {
		this.reservationDao = reservationDao;
		this.displayInfoDao = displayInfoDao;
	}
	
	@Override
	@Transactional
	public Integer saveReservation(ReservationRequestDto reservationRequestDto) {
		ReservationDto dto = reservationRequestDto.toDTO();
		Integer reservationInfoId = reservationDao.saveReservation(dto);
		List<ReservationPrice> prices = reservationRequestDto.getPrices();
		if (reservationInfoId != 0 & prices != null && prices.size() > 0)
			saveReservationPrice(reservationInfoId, prices);
		return 0;
	}

	@Override
	public void saveReservationPrice(Integer reservationInfoId, List<ReservationPrice> prices) {
		reservationDao.saveReservationPrice(reservationInfoId, prices);
	}

	@Override
	public List<ReservationResponseDto> selectReservationInfoByEmail(String email) {
		List<ReservationResponseDto> reservationResponseDtos = new ArrayList<>();
		List<ReservationDto> reservations = reservationDao.selectReservationInfoByEmail(email);
		ReservationResponseDto reservationResponseDto;
		for (ReservationDto reservationDto : reservations) {
			reservationResponseDto = reservationDto.toResponseDto(reservationDto);
			reservationResponseDto.setDisplayInfo(displayInfoDao.selectDisplayInfo(reservationDto.getDisplayInfoId()));
			reservationResponseDto.setTotalPrice(reservationDao.selectTotalPrice(reservationDto.getId()));
			reservationResponseDtos.add(reservationResponseDto);
		}
		
		return reservationResponseDtos;
	}

	@Override
	public void updateReservation(Integer reservationId) {
		reservationDao.updateReservation(reservationId);
	}
	
}
