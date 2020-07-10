package com.eunhasoo.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eunhasoo.reservation.dto.CommentRequestDto;

@Service
public class CommentServiceImpl implements CommentService {

	@Override
	public Integer insertComment(CommentRequestDto commentRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertCommentImage(Integer reservationInfoId, Integer commentId, MultipartFile imageFile) {
		// TODO Auto-generated method stub
		
	}

}
