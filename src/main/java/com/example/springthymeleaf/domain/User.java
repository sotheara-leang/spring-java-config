package com.example.springthymeleaf.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springthymeleaf.common.domain.AuditEntity;

@Table
@Entity
public class User extends AuditEntity implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = -5227739888866155529L;
	
	@Size(max = 15)
	private String username;
	
	@Size(max = 50)
	private String password;
	
	@Size(max = 50)
	@Column(name = "first_name")
	private String firstName;
	
	@Size(max = 15)
	@Column(name = "last_name")
	private String lastName;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Role role;
	
	@Transient
	private List<GrantedAuthority> authorities;

	@Override
	public void eraseCredentials() {
		this.password = null;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public List<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
