package com.eunhasoo.reservation.dto;

public class ProductPrice {

	private int discountRate;
	private int price;
	private int productId;
	private int productPriceId;
	private String priceTypeName;
	private String modifyDate;
	private String createDate;

	public ProductPrice(int discountRate, int price, int productId, int productPriceId, String priceTypeName,
			String modifyDate, String createDate) {
		super();
		this.discountRate = discountRate;
		this.price = price;
		this.productId = productId;
		this.productPriceId = productPriceId;
		this.priceTypeName = priceTypeName;
		this.modifyDate = modifyDate;
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public int getPrice() {
		return price;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public int getProductId() {
		return productId;
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	@Override
	public String toString() {
		return "ProductPrice [discountRate=" + discountRate + ", price=" + price + ", productId=" + productId
				+ ", productPriceId=" + productPriceId + ", priceTypeName=" + priceTypeName + ", modifyDate="
				+ modifyDate + ", createDate=" + createDate + "]";
	}
	
}
