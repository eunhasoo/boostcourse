package com.eunhasoo.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunhasoo.reservation.dao.ProductDao;
import com.eunhasoo.reservation.dto.ProductListDto;
import com.eunhasoo.reservation.dto.ProductsResponseDto;

@Service
public class ProductServiceImpl implements ProductService {
	
	static final int LIMIT = 4; // 한 페이지당 출력할 상품 개수

	@Autowired
	ProductDao productDao;
	
	@Override
	@Transactional(readOnly = true)
	public ProductsResponseDto getProducts(int categoryId, int start) {
		int totalCount = 0;
		List<ProductListDto> products = null;
		if (categoryId == 0) {
			products = productDao.selectAll(start, LIMIT);
			totalCount = productDao.selectCountAll();
		} else {
			products = productDao.selectById(categoryId, start, LIMIT);
			totalCount = productDao.selectCountById(categoryId);
		}
		return new ProductsResponseDto(totalCount, products);
	}

	@Override
	public int getCountAll() {
		return productDao.selectCountAll();
	}

	@Override
	public int getCountById(int categoryId) {
		return productDao.selectCountById(categoryId);
	}

}
