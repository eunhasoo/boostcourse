package com.eunhasoo.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.reservation.dto.CategoryListDto;
import com.eunhasoo.reservation.service.CategoryService;

@RestController
public class CategoryApiController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/api/categories")
	public Map<String, Object> categories() {
		List<CategoryListDto> categories = categoryService.getCategories();
		Map<String, Object> map = new HashMap<>();
		map.put("categories", categories);
		return map;
	}
}
