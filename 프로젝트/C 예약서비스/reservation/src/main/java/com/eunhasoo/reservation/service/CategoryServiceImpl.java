package com.eunhasoo.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunhasoo.reservation.dao.CategoryDao;
import com.eunhasoo.reservation.dto.CategoryListDto;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CategoryListDto> getCategories() {
		return categoryDao.selectAll();
	}

}
