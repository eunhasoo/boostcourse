package com.eunhasoo.reservation.dto;

public class ReservationResponseDto {

	private Integer displayInfoId;
	private Integer productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationDate;
	private String reservationTel;
	private Boolean cancelFlag;
	private String createDate;
	private String modifyDate;
	private Integer totalPrice;
	private DisplayInfo displayInfo;

	public ReservationResponseDto(Integer displayInfoId, Integer productId, String reservationEmail,
			String reservationName, String reservationDate, String reservationTel, Boolean cancelFlag,
			String createDate, String modifyDate) {
		this.displayInfoId = displayInfoId;
		this.productId = productId;
		this.reservationEmail = reservationEmail;
		this.reservationName = reservationName;
		this.reservationDate = reservationDate;
		this.reservationTel = reservationTel;
		this.cancelFlag = cancelFlag;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}
	
}
