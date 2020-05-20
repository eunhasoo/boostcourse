package com.eunhasoo.reservation.dto;

import java.util.List;

public class ProductsResponseDto {
	
	int totalCount;
	List<ProductListDto> products;
	
	public ProductsResponseDto(int totalCount, List<ProductListDto> products) {
		this.totalCount = totalCount;
		this.products = products;
	}
	
}
