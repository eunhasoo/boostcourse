package com.eunhasoo.reservation.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eunhasoo.reservation.dao.FileInfoDao;
import com.eunhasoo.reservation.dao.UserCommentDao;
import com.eunhasoo.reservation.dao.UserCommentImageDao;
import com.eunhasoo.reservation.dto.CommentRequestDto;

@Service
public class CommentServiceImpl implements CommentService {

	private final String ROOTPATH = "c:/tmp/reservation-web/user_img/";
	
	private final UserCommentDao userCommentDao;
	private final UserCommentImageDao userCommentImageDao;
	private final FileInfoDao fildInfoDao;
	
	@Autowired
	public CommentServiceImpl(UserCommentDao userCommentDao, UserCommentImageDao userCommentImageDao, 
			FileInfoDao fildInfoDao) {
		this.userCommentDao = userCommentDao;
		this.userCommentImageDao = userCommentImageDao;
		this.fildInfoDao = fildInfoDao;
	}
	
	@Transactional
	@Override
	public void saveComment(CommentRequestDto commentRequestDto, MultipartFile imageFile, 
			Integer reservationInfoId) throws RuntimeException {
		Integer userCommentId = userCommentDao.insertComment(commentRequestDto);
		if (imageFile != null) {
			Integer fileId = saveImageFile(imageFile, reservationInfoId);
			if (fileId == 0 || userCommentId == 0) 
				throw new RuntimeException("Insert query failed.");
			saveCommentImage(reservationInfoId, userCommentId, fileId);
		}
	}

	@Override
	public Integer saveImageFile(MultipartFile imageFile, Integer reservationInfoId) {
		String fileName = reservationInfoId + "_" 
							+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmm"))
							+ "." + imageFile.getOriginalFilename().split("\\.")[1];
		File userFile = new File(ROOTPATH + fileName);
		try {
			if (!userFile.exists()) {
				userFile.createNewFile();
			}
			imageFile.transferTo(userFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fildInfoDao.insertFile(userFile);
	}

	@Override
	public void saveCommentImage(Integer reservationInfoId, Integer userCommentId, Integer fileId) {
		userCommentImageDao.insertCommentImage(reservationInfoId, userCommentId, fileId);
	}

}
