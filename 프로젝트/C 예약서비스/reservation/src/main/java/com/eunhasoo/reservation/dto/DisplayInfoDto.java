package com.eunhasoo.reservation.dto;

import java.util.List;

// WEB API response로 
// 여러 객체들을 모아서 보낼 하나의 객체
public class DisplayInfoDto {

	private Double averageScore;
	private List<UserComment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public DisplayInfoDto(Double averageScore, List<UserComment> comments, DisplayInfo displayInfo,
			DisplayInfoImage displayInfoImage, List<ProductImage> productImage, List<ProductPrice> productPrice) {
		super();
		this.averageScore = averageScore;
		this.comments = comments;
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage;
		this.productImages = productImage;
		this.productPrices = productPrice;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public List<UserComment> getComments() {
		return comments;
	}

	public void setComments(List<UserComment> comments) {
		this.comments = comments;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public DisplayInfoImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImage displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<ProductImage> getProductImage() {
		return productImages;
	}

	public void setProductImage(List<ProductImage> productImage) {
		this.productImages = productImage;
	}

	public List<ProductPrice> getProductPrice() {
		return productPrices;
	}

	public void setProductPrice(List<ProductPrice> productPrice) {
		this.productPrices = productPrice;
	}

}
