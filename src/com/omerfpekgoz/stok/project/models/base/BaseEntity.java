package com.omerfpekgoz.stok.project.models.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
	
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private Date createdTime;
	private Date updatedTime;
	private Date deletedTime;
	
	@Column(name = "created_by", length = 50)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "updated_by", length = 50)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "deleted_by", length = 50)
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	
	@Column(name = "created_time", length = 50)
	@Temporal(TemporalType.DATE)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	@Column(name = "updated_time", length = 50)
	@Temporal(TemporalType.DATE)
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "deleted_time", length = 50)
	@Temporal(TemporalType.DATE)
	public Date getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(Date deletedTime) {
		this.deletedTime = deletedTime;
	}
	
	


}
