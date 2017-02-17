package com.example.springthymeleaf.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springthymeleaf.domain.Role;
import com.example.springthymeleaf.repository.role.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	@Override
	public Role findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}

	@Override
	public Role save(Role role) {
		return repository.save(role);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}
