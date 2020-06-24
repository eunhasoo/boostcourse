package com.eunhasoo.reservation.dto;

import java.util.List;

public class ReservationDto {
	
	private Integer displayInfoId;
	private Integer productId;
	private List<ReservationPrice> prices;
	private String reservationEmail;
	private String reservationName;
	private String reservationDate;
	private String reservationTel;
	private Integer id;
	private Boolean cancelFlag;
	private String createDate;
	private String modifyDate;
	
	public ReservationDto(Integer displayInfoId, Integer productId, List<ReservationPrice> prices,
			String reservationEmail, String reservationName, String reservationDate,
			String reservationTel, Integer id, Boolean cancelFlag, String createDate, String modifyDate) {
		this.displayInfoId = displayInfoId;
		this.productId = productId;
		this.prices = prices;
		this.reservationEmail = reservationEmail;
		this.reservationName = reservationName;
		this.reservationDate = reservationDate;
		this.reservationTel = reservationTel;
		this.id = id;
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

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean isCancelFlag() {
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
	
}
