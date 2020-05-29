package com.eunhasoo.daoexam.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.eunhasoo.daoexam.config.ApplicationConfig;
import com.eunhasoo.daoexam.dao.RoleDao;
import com.eunhasoo.daoexam.dto.Role;

public class JdbcTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		RoleDao dao = applicationContext.getBean(RoleDao.class);
		
		Role role = new Role();
		
//		<insert문 테스트>
		role.setRoleId(104);
		role.setDescription("CEO");
		int insertCount = dao.insert(role);
		System.out.println(insertCount + "건 입력되었습니다");
		
//		<update문 테스트>
		role.setRoleId(103);
		role.setDescription("CTO");
		int updateCount = dao.update(role);
		System.out.println(updateCount + "건 수정되었습니다");
		
//		<select문(1건) 테스트>
		Role resultRole = dao.selectById(103);
		System.out.println(resultRole);
		
//		<delete문 테스트>
		int deleteResult = dao.deleteById(103);
		System.out.println(deleteResult + "건 삭제하였습니다");
	}

}
