package com.OnlineClothMart.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class Category implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int cid;
	private String cname;

	@OneToMany(mappedBy="categoryobj")
	@JsonIgnore
	private List<Products> productList=new ArrayList<Products>();	 
	 
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@JsonIgnore
	public List<Products> getProductsList() {
		return productList;
	}

	
	public void setProductsList(List<Products> productList) {
		this.productList = productList;
	}
	
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	
	
	
}
