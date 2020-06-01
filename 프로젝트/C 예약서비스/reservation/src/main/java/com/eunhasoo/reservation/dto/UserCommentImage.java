package com.eunhasoo.reservation.dto;

import java.time.LocalDateTime;

public class UserCommentImage {

	private int fileId;
	private int imageId;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private boolean deleteFlag;
	private String contentType;
	private String saveFileName;
	private String modifyDate;
	private String createDate;

	public UserCommentImage(int fileId, int imageId, int reservationInfoId, int reservationUserCommentId,
			boolean deleteFlag, String contentType, String saveFileName, String modifyDate,
			String createDate) {
		super();
		this.fileId = fileId;
		this.imageId = imageId;
		this.reservationInfoId = reservationInfoId;
		this.reservationUserCommentId = reservationUserCommentId;
		this.deleteFlag = deleteFlag;
		this.contentType = contentType;
		this.saveFileName = saveFileName;
		this.modifyDate = modifyDate;
		this.createDate = createDate;
	}

	public String getContentType() {
		return contentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public int getFileId() {
		return fileId;
	}

	public int getImageId() {
		return imageId;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	@Override
	public String toString() {
		return "UserCommentImage [fileId=" + fileId + ", imageId=" + imageId + ", reservationInfoId="
				+ reservationInfoId + ", reservationUserCommentId=" + reservationUserCommentId + ", deleteFlag="
				+ deleteFlag + ", contentType=" + contentType + ", saveFileName=" + saveFileName + ", modifyDate="
				+ modifyDate + ", createDate=" + createDate + "]";
	}

}
