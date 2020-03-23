package com.omerfpekgoz.stok.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omerfpekgoz.stok.project.models.base.BaseEntity;

@Entity
@Table(name ="kategori")
public class Category extends BaseEntity {

	private int id;
	private String name;
	private int parentId;
	
	
	@Id
	@SequenceGenerator(name = "seq_category_id",allocationSize = 1,sequenceName = "seq_category")
	@GeneratedValue(generator =  "seq_category_id",strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "Adý")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return id + "-"+ name  ;
	}

	
	
}
