package com.eunhasoo.reservation.dto;

public class UserComment {

	private int commentId;
	private int productId;
	private int reservationInfoId;
	private int score;
	private String comment;
	private String email;
	private String reservationName;
	private String reservationTelephone;
	private String reservationDate;
	private String createDate;
	private String modifyDate;
	private UserCommentImage userCommentImage;

	public UserComment(int commentId, int productId, int reservationInfoId, int score, String comment, String email,
			String reservationName, String reservationTelephone, String reservationDate,
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
	}

	public void setUserCommentImage(UserCommentImage userCommentImage) {
		this.userCommentImage = userCommentImage;
	}

	public int getCommentId() {
		return commentId;
	}

	public int getProductId() {
		return productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public int getScore() {
		return score;
	}

	public String getComment() {
		return comment;
	}

	public String getEmail() {
		return email;
	}

	public String getReservationName() {
		return reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public UserCommentImage getUserCommentImage() {
		return userCommentImage;
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
