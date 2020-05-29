package com.eunhasoo.jdbcexam;

import com.eunhasoo.jdbcexam.dao.RoleDao;

public class JDBCExam4 {

	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
		int deleteCount = dao.deleteRoleById(106);
		System.out.println(deleteCount);
	}

}
