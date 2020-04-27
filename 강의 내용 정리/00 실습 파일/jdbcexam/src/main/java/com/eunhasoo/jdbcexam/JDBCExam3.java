package com.eunhasoo.jdbcexam;

import java.util.List;

import com.eunhasoo.jdbcexam.dao.RoleDao;
import com.eunhasoo.jdbcexam.dto.Role;

public class JDBCExam3 {

	public static void main(String[] args) {
		RoleDao roleDao = new RoleDao();
		List<Role> roleList = roleDao.getRoles();
		for (Role role : roleList) {
			System.out.println(role.toString());
		}
	}

}
