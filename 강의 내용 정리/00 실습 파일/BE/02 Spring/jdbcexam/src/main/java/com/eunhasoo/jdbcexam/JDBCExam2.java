package com.eunhasoo.jdbcexam;

import com.eunhasoo.jdbcexam.dao.RoleDao;
import com.eunhasoo.jdbcexam.dto.Role;

public class JDBCExam2 {

	public static void main(String[] args) {
		int roleId = 105;
		String description = "UI Designer";
		Role role = new Role(roleId, description);
		
		RoleDao roleDao = new RoleDao();
		int insertCount = roleDao.addRole(role);
		System.out.println(insertCount);
	}

}
