package com.eunhasoo.reservation.service;

import org.springframework.web.multipart.MultipartFile;

import com.eunhasoo.reservation.dto.CommentRequestDto;

public interface CommentService {
	public Integer insertComment(CommentRequestDto commentRequestDto);
	public void insertCommentImage(Integer reservationInfoId, Integer commentId, MultipartFile imageFile);
}
