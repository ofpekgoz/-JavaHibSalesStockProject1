package com.omerfpekgoz.stok.project.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omerfpekgoz.stok.project.models.base.BaseEntity;

@Entity
@Table(name = "personel")
public class Staff extends BaseEntity {         

	private int id;
	private String password;
	private String nameSurname;
	private String email;
	private String phoneNumber;
	private UserRoleTypes role;
	
	@Id
	@SequenceGenerator(name = "seq_staff_id",allocationSize = 1,sequenceName = "seq_staff")
	@GeneratedValue(generator =  "seq_staff_id",strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNameSurname() {
		return nameSurname;
	}
	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Enumerated
	@Column(name = "role_id")
	public UserRoleTypes getRole() {
		return role;
	}
	public void setRole(UserRoleTypes role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return id + "-" + nameSurname + "-" + role ;
	}
	
	
	
}
