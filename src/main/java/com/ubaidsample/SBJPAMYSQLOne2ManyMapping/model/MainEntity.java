package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MainEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "createByAddress", updatable = false)
	@NotNull
	private String createByAddress;

	@Column(name = "createBy", updatable = false)
	private String createBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", insertable = true, updatable = false)
	@CreatedDate
	@NotNull
	private Date createDate;

	@Column(name = "updateByAddress", updatable = false)
	@NotNull
	private String updateByAddress;

	@Column(name = "updateBy", updatable = false)
	private String updateBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", updatable = true)
	@LastModifiedDate
	@NotNull
	private Date updateDate;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

	// Method for Last Update Date
	@PrePersist
	public void prePersist() {
		Date now = new Date();
		this.createDate = now;
		this.updateDate = now;
	}

	@PreUpdate
	public void preUpdate() {
		this.updateDate = new Date();
	}

	// Getters & Setters - Start
	public String getCreateByAddress() {
		return createByAddress;
	}

	public void setCreateByAddress(String createByAddress) {
		this.createByAddress = createByAddress;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateByAddress() {
		return updateByAddress;
	}

	public void setUpdateByAddress(String updateByAddress) {
		this.updateByAddress = updateByAddress;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	// Getters & Setters - End
}