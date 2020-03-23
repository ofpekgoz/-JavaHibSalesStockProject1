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
@Table(name = "stok")
public class Stock extends BaseEntity {

	private int id;
	private Staff staff;		//Personel
	private Products products;    //Ürünler
	private Date stockdate;
	private int stockPiece; //Stok Adedi						

	
	
	@Id
	@SequenceGenerator(name = "seq_stock_id",allocationSize = 1,sequenceName = "seq_stock")
	@GeneratedValue(generator =  "seq_stock_id",strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@JoinColumn(name = "products_id")
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	@Temporal(TemporalType.DATE)
	public Date getStockdate() {
		return stockdate;
	}
	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}
	
	
	
	public int getStockPiece() {
		return stockPiece;
	}
	public void setStockPiece(int stockPiece) {
		this.stockPiece = stockPiece;
	}
	@Override
	public String toString() {
		return id +"-"+products ;
	}
	
	
	
}
