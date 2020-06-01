package com.eunhasoo.reservation.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eunhasoo.reservation.config.ApplicationConfig;

public class DisplayInfoDaoTest {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//		
//		DisplayInfoDao dao = context.getBean(DisplayInfoDao.class);
//		DisplayInfo info = dao.selectDisplayInfo(1);
//		System.out.println(info.toString());
//		
//		UserCommentDao dao = context.getBean(UserCommentDao.class);
//		List<UserComment> list = dao.selectUserComment(1);
//		for (UserComment userComment : list)
//			System.out.println(userComment.toString());
//		System.out.println(dao.selectAvgScore(3));
//		
		DisplayInfoImageDao dao = context.getBean(DisplayInfoImageDao.class);
		System.out.println(dao.selectDisplayInfoImage(1));
//		
//		ProductImageDao dao = context.getBean(ProductImageDao.class);
//		List<ProductImage> list = dao.selectProductImage(4);
//		for (ProductImage p : list)
//			System.out.println(p);
//		
//		ProductPriceDao dao = context.getBean(ProductPriceDao.class);
//		List<ProductPrice> list = dao.selectProductPrice(1);
//		for (ProductPrice p : list)
//			System.out.println(p);
//		
//		UserCommentImageDao dao = context.getBean(UserCommentImageDao.class);
//		System.out.println(dao.selectUserCommentImage(1));
		
	}

}
