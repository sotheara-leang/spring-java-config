package com.example.springthymeleaf.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springthymeleaf.domain.User;

public interface UserService extends UserDetailsService {

	User findByUsername(String username);
	
	User findOne(Long id);
	
	List<User> findAll();
	
	User save(User user);
	
	void delete(Long id);
}
