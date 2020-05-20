package com.eunhasoo.reservation.dto;

import java.sql.Date;

public class ProductDto {

	private int id;
	private int categoryId;
	private String description;
	private String content;
	private String event;
	private Date createDate;
	private Date modifyDate;

	public ProductDto(int id, int categoryId, String description, String content, String event, Date createDate,
			Date modifyDate) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.description = description;
		this.content = content;
		this.event = event;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public int getId() {
		return id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content;
	}

	public String getEvent() {
		return event;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

}
