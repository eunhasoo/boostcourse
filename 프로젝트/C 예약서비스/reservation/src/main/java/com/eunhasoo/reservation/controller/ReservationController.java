package com.eunhasoo.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.reservation.dto.ReservationRequestDto;
import com.eunhasoo.reservation.service.ReservationService;

@RestController
public class ReservationController {

	private final ReservationService reservationService;
	
	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping("/api/reservations")
	public void reservations(@RequestBody ReservationRequestDto reservationRequestDto) {
		reservationService.saveReservation(reservationRequestDto);
	}
	
}
