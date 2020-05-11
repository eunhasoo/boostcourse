package com.eunhasoo.daoexam.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eunhasoo.daoexam.config.ApplicationConfig;
import com.eunhasoo.daoexam.dao.RoleDao;
import com.eunhasoo.daoexam.dto.Role;

public class SelectAllTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleDao dao = applicationContext.getBean(RoleDao.class);
		List<Role> roles = dao.selectAll();
		for (Role role : roles)
			System.out.println(role);
	}

}
