package com.eunhasoo.reservation.service;

import com.eunhasoo.reservation.dto.ProductsResponseDto;

public interface ProductService {
	public ProductsResponseDto getProducts(int categoryId, int start);
	public int getCountAll();
	public int getCountById(int categoryId);
}
