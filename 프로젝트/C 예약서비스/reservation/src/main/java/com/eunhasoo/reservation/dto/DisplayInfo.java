package com.eunhasoo.reservation.dto;

public class DisplayInfo {

	private int categoryId;
	private String categoryName;
	private String createDate;
	private int displayInfoId;
	private String email;
	private String homepage;
	private String modifyDate;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String productContent;
	private String productDescription;
	private String productEvent;
	private int productId;
	private String telephone;

	public DisplayInfo(int categoryId, String categoryName, String createDate, int displayInfoId, String email,
			String homepage, String modifyDate, String openingHours, String placeLot, String placeName,
			String placeStreet, String productContent, String productDescription, String productEvent, int productId,
			String telephone) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createDate = createDate;
		this.displayInfoId = displayInfoId;
		this.email = email;
		this.homepage = homepage;
		this.modifyDate = modifyDate;
		this.openingHours = openingHours;
		this.placeLot = placeLot;
		this.placeName = placeName;
		this.placeStreet = placeStreet;
		this.productContent = productContent;
		this.productDescription = productDescription;
		this.productEvent = productEvent;
		this.productId = productId;
		this.telephone = telephone;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public String getEmail() {
		return email;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public String getProductContent() {
		return productContent;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public int getProductId() {
		return productId;
	}

	public String getTelephone() {
		return telephone;
	}

	@Override
	public String toString() {
		return "DisplayInfo [categoryId=" + categoryId + ", categoryName=" + categoryName + ", createDate=" + createDate
				+ ", displayInfoId=" + displayInfoId + ", email=" + email + ", homepage=" + homepage + ", modifyDate="
				+ modifyDate + ", openingHours=" + openingHours + ", placeLot=" + placeLot + ", placeName=" + placeName
				+ ", placeStreet=" + placeStreet + ", productContent=" + productContent + ", productDescription="
				+ productDescription + ", productEvent=" + productEvent + ", productId=" + productId + ", telephone="
				+ telephone + "]";
	}

}
