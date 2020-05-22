package com.eunhasoo.reservation.service;

import java.util.List;

import com.eunhasoo.reservation.dto.ProductListDto;

public interface ProductService {
	public List<ProductListDto> getProducts(int categoryId, int start);
	public int getCount(int categoryId);
}
