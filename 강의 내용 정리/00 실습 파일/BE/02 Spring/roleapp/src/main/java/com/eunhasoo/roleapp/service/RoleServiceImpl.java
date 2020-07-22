package com.eunhasoo.roleapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eunhasoo.roleapp.dao.RoleDao;
import com.eunhasoo.roleapp.dto.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;
	
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Override
	public List<Role> getRoles() {
		return roleDao.selectAll();
	}

}
