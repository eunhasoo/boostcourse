package com.eunhasoo.jdbcexam;

import com.eunhasoo.jdbcexam.dao.RoleDao;
import com.eunhasoo.jdbcexam.dto.Role;

public class JDBCExam5 {

	public static void main(String[] args) {
		Role role = new Role(104, "Planner");
		RoleDao dao = new RoleDao();
		int deleteCount = dao.updateRoleDescription(role);
		System.out.println(deleteCount);
	}

}
