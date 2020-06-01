package com.eunhasoo.reservation.dto;

public class DisplayInfoImage {

	private int displayInfoId;
	private int displayInfoImageId;
	private int fileId;
	private boolean deleteFlage;
	private String contentType;
	private String fileName;
	private String saveFileName;
	private String createDate;
	private String modifyDate;

	public DisplayInfoImage(int displayInfoId, int displayInfoImageId, int fileId, boolean deleteFlage,
			String contentType, String fileName, String saveFileName, String createDate,
			String modifyDate) {
		super();
		this.displayInfoId = displayInfoId;
		this.displayInfoImageId = displayInfoImageId;
		this.fileId = fileId;
		this.deleteFlage = deleteFlage;
		this.contentType = contentType;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public String getContentType() {
		return contentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public boolean isDeleteFlage() {
		return deleteFlage;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public int getDisplayInfoImageId() {
		return displayInfoImageId;
	}

	public void setDisplayInfoImageId(int displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}

	public int getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	@Override
	public String toString() {
		return "DisplayInfoImage [displayInfoId=" + displayInfoId + ", fileId=" + fileId + ", deleteFlage="
				+ deleteFlage + ", contentType=" + contentType + ", fileName=" + fileName + ", saveFileName="
				+ saveFileName + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}

}
