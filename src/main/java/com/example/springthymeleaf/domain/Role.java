package com.example.springthymeleaf.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.springthymeleaf.common.domain.AuditEntity;

@Table
@Entity
public class Role extends AuditEntity {

	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
