package com.example.springthymeleaf.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springthymeleaf.domain.User;
import com.example.springthymeleaf.repository.user.UserRepository;

// Mark this class as service
@Service
public class UserServiceImpl implements UserService {
	
	// inject user repository
	@Autowired
	private UserRepository repository;
	
	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	@Override
	public User findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}
