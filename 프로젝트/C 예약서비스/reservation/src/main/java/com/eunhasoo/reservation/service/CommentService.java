package com.eunhasoo.reservation.service;

import org.springframework.web.multipart.MultipartFile;

import com.eunhasoo.reservation.dto.CommentRequestDto;

public interface CommentService {
	public void saveComment(CommentRequestDto commentRequestDto, MultipartFile imageFile, Integer reservationInfoId);
	public Integer saveImageFile(MultipartFile imageFile, Integer reservationInfoId);
	public void saveCommentImage(Integer reservationInfoId, Integer userCommentId, Integer fileId);
}
