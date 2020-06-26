package com.eunhasoo.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.reservation.dto.ReservationRequestDto;
import com.eunhasoo.reservation.dto.ReservationResponseDto;
import com.eunhasoo.reservation.service.ReservationService;

@RestController
public class ReservationApiController {

	private final ReservationService reservationService;
	
	@Autowired
	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping("/api/reservations")
	public void reservations(@RequestBody ReservationRequestDto reservationRequestDto) {
		reservationService.saveReservation(reservationRequestDto);
	}
	
	@GetMapping("/api/reservations")
	public Map<String, Object> reservations(@RequestParam("resrv_email") String email, HttpSession httpSession) {
		List<ReservationResponseDto> reservations = reservationService.selectReservationInfoByEmail(email);
		Integer size = reservations.size();
		if (size > 0) {
			httpSession.setAttribute("userEmail", email);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("reservations", reservations);
		map.put("size", size);
		return map;
	}
	
	@PutMapping("/api/reservations/{reservationId}")
	public void reservations(@PathVariable Integer reservationId) {
		reservationService.updateReservation(reservationId);
	}
	
}
