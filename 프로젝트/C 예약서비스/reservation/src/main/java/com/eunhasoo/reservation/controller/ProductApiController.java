package com.eunhasoo.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.reservation.dto.DisplayInfoDto;
import com.eunhasoo.reservation.dto.ProductListDto;
import com.eunhasoo.reservation.service.DisplayInfoService;
import com.eunhasoo.reservation.service.ProductService;

@RestController
public class ProductApiController {

	@Autowired
	ProductService productService;
	
	@Autowired
	DisplayInfoService displayInfoService;
	
	@GetMapping("/api/products")
	public Map<String, Object> products(@RequestParam(required = false, defaultValue = "0") int categoryId, 
			@RequestParam(required = false, defaultValue = "0") int start) {
		List<ProductListDto> products = productService.getProducts(categoryId, start);
		int count = productService.getCount(categoryId);
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", count);
		map.put("items", products);
		return map;
	}
	
	@GetMapping("/api/products/{displayInfoId}")
	public Map<String, Object> productsDisplayInfo(@PathVariable int displayInfoId) {
		Map<String, Object> map = new HashMap<>();
		DisplayInfoDto dto = displayInfoService.getDisplayInfoDto(displayInfoId);
		map.put("displayInfoResponse", dto);
		return map;
	}
}
