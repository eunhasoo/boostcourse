package com.eunhasoo.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationRequestDto {

	private Integer displayInfoId;
	private Integer productId;
	private List<ReservationPrice> prices;
	private String reservationEmail;
	private String reservationName;
	private String reservationYearMonthDay;
	private String reservationTelephone;

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

	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}

	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	@Override
	public String toString() {
		return "ReservationRequestDto [displayInfoId=" + displayInfoId + ", productId=" + productId + ", prices="
				+ prices.get(0) + ", reservationEmail=" + reservationEmail + ", reservationName=" + reservationName
				+ ", reservationYearMonthDay=" + reservationYearMonthDay + ", reservationTelephone="
				+ reservationTelephone + "]";
	}

	public ReservationDto toDTO() {
		String now = LocalDateTime.now().toString();
		return new ReservationDto(this.displayInfoId, this.productId, this.reservationEmail, this.reservationName,
				this.reservationYearMonthDay, this.reservationTelephone, 0, false, now, now);
	}
}
