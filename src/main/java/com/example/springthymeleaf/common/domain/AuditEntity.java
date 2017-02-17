package com.example.springthymeleaf.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.springthymeleaf.domain.User;
import com.example.springthymeleaf.frmk.domain.Auditable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity implements Auditable<User, Long> {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@CreatedDate
	@Column(name = "create_date")
	private Date createDate;
	
	@LastModifiedDate
	@Column(name = "update_date")
	private Date updateDate;
	
	@CreatedBy
	@OneToOne(fetch = FetchType.LAZY)
	private User creator;
	
	@LastModifiedBy
	@OneToOne(fetch = FetchType.LAZY)
	private User updater;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public User getCreator() {
		return creator;
	}

	@Override
	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public Date getUpdateDate() {
		return updateDate;
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public User getUpdater() {
		return updater;
	}

	@Override
	public void setUpdater(User updater) {
		this.updater = updater;
	}
}
