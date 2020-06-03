package com.eunhasoo.reservation.dto;

public class UserComment {

	private Integer commentId;
	private Integer productId;
	private Integer reservationInfoId;
	private Integer score;
	private String comment;
	private String email;
	private String reservationName;
	private String reservationTelephone;
	private String reservationDate;
	private String createDate;
	private String modifyDate;
	private UserCommentImage userCommentImage;

	public UserComment(Integer commentId, Integer productId, Integer reservationInfoId, Integer score, String comment,
			String email, String reservationName, String reservationTelephone, String reservationDate,
			String createDate, String modifyDate) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
		this.comment = comment;
		this.email = email;
		this.reservationName = reservationName;
		this.reservationTelephone = reservationTelephone;
		this.reservationDate = reservationDate;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.userCommentImage = userCommentImage;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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

	public UserCommentImage getUserCommentImage() {
		return userCommentImage;
	}

	public void setUserCommentImage(UserCommentImage userCommentImage) {
		this.userCommentImage = userCommentImage;
	}

	@Override
	public String toString() {
		return "UserComment [commentId=" + commentId + ", productId=" + productId + ", reservationInfoId="
				+ reservationInfoId + ", score=" + score + ", comment=" + comment + ", email=" + email
				+ ", reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone
				+ ", reservationDate=" + reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", userCommentImage=" + userCommentImage + "]";
	}

}
