package com.eunhasoo.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.reservation.dto.PromotionDto;
import com.eunhasoo.reservation.service.PromotionService;

@RestController
public class PromotionApiController {

	@Autowired
	PromotionService promotionService;
	
	@GetMapping("/api/promotions")
	public Map<String, Object> promotions() {
		List<PromotionDto> promotions = promotionService.getPromotions();
		Map<String, Object> map = new HashMap<>();
		map.put("items", promotions);
		return map;
	}
	
}
