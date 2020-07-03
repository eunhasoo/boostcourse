package com.eunhasoo.reservation.dto;

public class ReservationResponseDto {

	private Integer displayInfoId;
	private Integer productId;
	private Integer reservationInfoId;
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
			String createDate, String modifyDate, Integer reservationInfoId) {
		this.displayInfoId = displayInfoId;
		this.productId = productId;
		this.reservationEmail = reservationEmail;
		this.reservationName = reservationName;
		this.reservationDate = reservationDate;
		this.reservationTel = reservationTel;
		this.cancelFlag = cancelFlag;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}
	
}
