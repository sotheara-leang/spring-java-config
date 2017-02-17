package com.example.springthymeleaf.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.springthymeleaf.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QueryDslPredicateExecutor<Role> {
	
}
