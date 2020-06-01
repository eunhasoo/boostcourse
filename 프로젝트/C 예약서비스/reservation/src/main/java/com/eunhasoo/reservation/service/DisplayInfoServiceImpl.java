package com.eunhasoo.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eunhasoo.reservation.dao.DisplayInfoDao;
import com.eunhasoo.reservation.dao.DisplayInfoImageDao;
import com.eunhasoo.reservation.dao.ProductImageDao;
import com.eunhasoo.reservation.dao.ProductPriceDao;
import com.eunhasoo.reservation.dao.UserCommentDao;
import com.eunhasoo.reservation.dao.UserCommentImageDao;
import com.eunhasoo.reservation.dto.DisplayInfo;
import com.eunhasoo.reservation.dto.DisplayInfoDto;
import com.eunhasoo.reservation.dto.DisplayInfoImage;
import com.eunhasoo.reservation.dto.ProductImage;
import com.eunhasoo.reservation.dto.ProductPrice;
import com.eunhasoo.reservation.dto.UserComment;
import com.eunhasoo.reservation.dto.UserCommentImage;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDao;
	
	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;
	
	@Autowired 
	private ProductPriceDao productPriceDao;
	
	@Autowired
	private ProductImageDao productImageDao;
	
	@Autowired
	private UserCommentImageDao userCommentImageDao;
	
	@Autowired
	private UserCommentDao userCommentDao;
	
	// null exception 해주기!
	
	@Override
	public DisplayInfoDto getDisplayInfoDto(int displayInfoId) {
		double averageScore = userCommentDao.selectAvgScore(displayInfoId);
		List<UserComment> comments = userCommentDao.selectUserComment(displayInfoId);
		DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoImageDao.selectDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = productImageDao.selectProductImage(displayInfoId);
		List<ProductPrice> productPrices = productPriceDao.selectProductPrice(displayInfoId);
		
		UserCommentImage userCommentImage;
		for (UserComment comment : comments) {
			if (comment == null) continue;
			int id = comment.getCommentId();
			userCommentImage = userCommentImageDao.selectUserCommentImage(id);
			comment.setUserCommentImage(userCommentImage);
		}
		
		return new DisplayInfoDto(averageScore, comments,
								displayInfo, displayInfoImage, 
								productImages, productPrices
		);
	}


}
