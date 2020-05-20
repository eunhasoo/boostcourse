package com.eunhasoo.reservation.service;

import java.util.List;

import com.eunhasoo.reservation.dto.CategoryListDto;

public interface CategoryService {
	List<CategoryListDto> getCategories();
}
