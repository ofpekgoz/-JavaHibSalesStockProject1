package com.omerfpekgoz.stok.project.models;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "ürünler")
public class Products extends BaseEntity {
	
	private int id;
	private String name;
	private Category category;  //Kategori
	private float price;    //Fiyat
	private Date date;
	private int piece;   //Adet
	
	
	
	@Id
	@SequenceGenerator(name = "seq_urun_id",allocationSize = 1,sequenceName = "seq_urun")
	@GeneratedValue(generator =  "seq_urun_id",strategy = GenerationType.SEQUENCE)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name =name;
	}
	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date= date;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	

	public int getPiece() {
		return piece;
	}
	public void setPiece(int piece) {
		this.piece = piece;
	}
	@Override
	public String toString() {
		return id + "-" + name ;
	}
	
	
	

}
