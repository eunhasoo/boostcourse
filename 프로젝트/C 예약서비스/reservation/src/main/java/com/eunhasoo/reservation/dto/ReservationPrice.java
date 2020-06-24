package com.eunhasoo.reservation.dto;

public class ReservationPrice {

	Integer count; 					// 예약 상품 수
	Integer productPriceId; 		// 상품 가격 ID
	Integer reservationInfoId; 		// 예약 ID
	Integer reservationInfoPriceId; // 예약 가격 ID

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(Integer reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "ReservationPrice [count=" + count + ", productPriceId=" + productPriceId + ", reservationInfoId="
				+ reservationInfoId + ", reservationInfoPriceId=" + reservationInfoPriceId + "]";
	}
	
}
