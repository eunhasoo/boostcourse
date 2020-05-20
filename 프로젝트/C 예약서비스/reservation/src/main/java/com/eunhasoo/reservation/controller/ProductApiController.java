package com.eunhasoo.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.reservation.dto.ProductsResponseDto;
import com.eunhasoo.reservation.service.ProductService;

@RestController
public class ProductApiController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/api/products/{categoryId}")
	public Map<String, Object> products(@RequestParam(required = false, defaultValue = "0") int categoryId, 
			@RequestParam(required = false, defaultValue = "0") int start) {
		ProductsResponseDto products = productService.getProducts(categoryId, start);
		Map<String, Object> map = new HashMap<>();
		map.put("products", products);
		return map;
	}
}
