package com.example.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.springthymeleaf.domain.User;

/**
 * Mark this class as repository
 * extends QueryDslPredicateExecutor to work with query dsl
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
	
	User findByUsername(String username);
	
	
}
