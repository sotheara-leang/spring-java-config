package com.example.springthymeleaf.service.role;

import java.util.List;

import com.example.springthymeleaf.domain.Role;

public interface RoleService {

	Role findOne(Long id);
	
	List<Role> findAll();
	
	Role save(Role role);
	
	void delete(Long id);
}
