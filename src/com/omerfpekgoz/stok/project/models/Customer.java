package com.omerfpekgoz.stok.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.omerfpekgoz.stok.project.models.base.BaseEntity;

@Entity
@Table(name ="müþteri")
public class Customer extends BaseEntity {
	
	private int id;
	private String nameSurname;
	private String phoneNumber;
	private String adress;
	
	@Id
	@SequenceGenerator(name = "seq_customer_id",allocationSize = 1,sequenceName = "seq_customer")
	@GeneratedValue(generator =  "seq_customer_id",strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameSurname() {
		return nameSurname;
	}
	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	@Override
	public String toString() {
		return id + "-"+ nameSurname ;
				
	}
	
	
	

}
