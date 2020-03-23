package com.omerfpekgoz.stok.project.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.omerfpekgoz.stok.project.models.base.BaseEntity;

@Entity
@Table(name = "satýþ")
public class Sales extends BaseEntity {    //Satýþ

	private int id;
	private Customer customer;   //Müþteri
	private Staff staff;         //Personel
	private Products products;	//Ürün
	
	private int salesPiece;        //Adet
	private Date salesDate;    //Satýþ tarihi
	
	
	
	
	@Id
	@SequenceGenerator(name = "seq_sales_id",allocationSize = 1,sequenceName = "seq_sales")
	@GeneratedValue(generator =  "seq_sales_id",strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@ManyToOne
	@JoinColumn(name = "staff_id")
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	
	
	public int getSalesPiece() {
		return salesPiece;
	}
	public void setSalesPiece(int salesPiece) {
		this.salesPiece = salesPiece;
	}
	@Temporal(TemporalType.DATE)
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	@Override
	public String toString() {
		return id + " " + customer + " " + staff + " " + products +" " + salesPiece + " " + salesDate;
	}
	
	
	
}
