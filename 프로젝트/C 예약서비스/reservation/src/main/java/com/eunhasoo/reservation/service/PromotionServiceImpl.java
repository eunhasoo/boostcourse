package com.eunhasoo.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eunhasoo.reservation.dao.PromotionDao;
import com.eunhasoo.reservation.dto.PromotionDto;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	PromotionDao promotionDao;
	
	@Override
	public List<PromotionDto> getPromotions() {
		return promotionDao.selectAll();
	}

}
