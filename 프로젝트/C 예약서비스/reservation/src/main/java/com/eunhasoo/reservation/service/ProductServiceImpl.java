package com.eunhasoo.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunhasoo.reservation.dao.ProductDao;
import com.eunhasoo.reservation.dto.ProductListDto;

@Service
public class ProductServiceImpl implements ProductService {
	
	static final int LIMIT = 4; // 한 번에 출력할 상품 개수

	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductListDto> getProducts(int categoryId, int start) {
		List<ProductListDto> products = null;
		if (categoryId == 0) {
			products = productDao.selectAll(start, LIMIT);
		} else {
			products = productDao.selectById(categoryId, start, LIMIT);
		}
		return products;
	}

	@Override
	public int getCount(int categoryId) {
		int count = 0;
		if (categoryId == 0) {
			count = productDao.selectCountAll();
		} else {
			count = productDao.selectCountById(categoryId);
		}
		return count;
	}

}
