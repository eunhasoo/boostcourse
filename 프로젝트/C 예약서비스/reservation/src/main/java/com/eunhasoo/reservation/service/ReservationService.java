package com.eunhasoo.reservation.service;

import java.util.List;

import com.eunhasoo.reservation.dto.ReservationPrice;
import com.eunhasoo.reservation.dto.ReservationRequestDto;
import com.eunhasoo.reservation.dto.ReservationResponseDto;

public interface ReservationService {
	public Integer saveReservation(ReservationRequestDto reservationDto);
	public void saveReservationPrice(Integer reservationInfoId, List<ReservationPrice> prices);
	public List<ReservationResponseDto> selectReservationInfoByEmail(String email);
	public void updateReservation(Integer reservationId);
}
