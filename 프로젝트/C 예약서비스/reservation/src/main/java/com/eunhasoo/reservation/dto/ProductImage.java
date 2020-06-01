package com.eunhasoo.reservation.dto;

public class ProductImage {

	private int productId;
	private int fileInfoId;
	private int productImageId;
	private boolean deleteFlag;
	private String contentType;
	private String fileName;
	private String saveFileName;
	private String type;
	private String modifyDate;
	private String createDate;

	public ProductImage(int productId, int fileInfoId, int productImageId, boolean deleteFlag, String contentType,
			String fileName, String saveFileName, String type, String modifyDate, String createDate) {
		super();
		this.productId = productId;
		this.fileInfoId = fileInfoId;
		this.productImageId = productImageId;
		this.deleteFlag = deleteFlag;
		this.contentType = contentType;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.type = type;
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

	public int getFileInfoId() {
		return fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "ProductImage [productId=" + productId + ", fileInfoId=" + fileInfoId + ", productImageId="
				+ productImageId + ", deleteFlag=" + deleteFlag + ", contentType=" + contentType + ", fileName="
				+ fileName + ", saveFileName=" + saveFileName + ", type=" + type + ", modifyDate=" + modifyDate
				+ ", createDate=" + createDate + "]";
	}

}
