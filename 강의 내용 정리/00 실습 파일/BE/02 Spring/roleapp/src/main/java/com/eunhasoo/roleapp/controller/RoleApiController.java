package com.eunhasoo.roleapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eunhasoo.roleapp.dto.Role;
import com.eunhasoo.roleapp.service.RoleService;

@RestController
public class RoleApiController {

	private final RoleService roleService;
	
	public RoleApiController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("/api/roles")
	public List<Role> roles() {
		return roleService.getRoles();
	}
}
