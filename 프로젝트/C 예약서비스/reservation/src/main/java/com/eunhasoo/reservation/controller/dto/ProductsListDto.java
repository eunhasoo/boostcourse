package com.eunhasoo.reservation.controller.dto;

public class ProductsListDto {
	
	private int displayInfoId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private int productId;
	private String productImageUrl;
	private int totalCount;
	
	public ProductsListDto(int displayInfoId, String placeName, String productContent, String productDescription,
			int productId, String productImageUrl) {
		this.displayInfoId = displayInfoId;
		this.placeName = placeName;
		this.productContent = productContent;
		this.productDescription = productDescription;
		this.productId = productId;
		this.productImageUrl = productImageUrl;
	}

}
