package com.eunhasoo.roleapp;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eunhasoo.roleapp.dto.Role;
import com.eunhasoo.roleapp.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {

	@Autowired
	RoleService roleService;
	
	@Test
	public void getRolesTest() {
		List<Role> roles = roleService.getRoles();
		Assert.assertEquals(roles.size(), 3);
	}
}
