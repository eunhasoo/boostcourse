package com.eunhasoo.reservation.service;

import java.util.List;

import com.eunhasoo.reservation.dto.ReservationPrice;
import com.eunhasoo.reservation.dto.ReservationRequestDto;

public interface ReservationService {

	public Integer saveReservation(ReservationRequestDto reservationDto);
	public void saveReservationPrice(Integer reservationInfoId, List<ReservationPrice> prices);
	
}
