package com.eunhasoo.roleapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.eunhasoo.roleapp.service.RoleService;

@Controller
public class RoleController {

	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("/roles")
	public String roles(ModelMap modelMap) {
		modelMap.addAttribute("roles", roleService.getRoles());
		return "roles";
	}
}
