package com.example.springthymeleaf.user;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.ListUtils;

import com.example.springthymeleaf.AbstractTest;
import com.example.springthymeleaf.domain.QUser;
import com.example.springthymeleaf.domain.User;
import com.example.springthymeleaf.repository.UserRepository;
import com.mysema.query.types.Predicate;

public class TestUser extends AbstractTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindUserByUsername() {
		User user = userRepository.findByUsername("admin");
		Assert.assertNotNull(user);
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("testuser");
		user.setLastName("testlastname");
		user.setFirstName("testfirstname");

		userRepository.save(user);
		Assert.assertNotNull(user.getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCriteria() {
		List<User> users = (List<User>) ListUtils.toList(
			userRepository.findAll(QUser.user.lastName.startsWith("Lay"))
		);
		Assert.assertEquals(2, users.size());
	}

	@Test
	public void testCriteria2() {
		Predicate predicate = QUser.user.lastName.startsWith("Lay").and(QUser.user.password.eq("123"));
		User user = userRepository.findOne(predicate);
		Assert.assertNotNull(user);
	}
}
