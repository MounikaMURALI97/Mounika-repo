package com.OnlineClothMart.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private int rid;
	private int rating;
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="pid")
	@JsonIgnore
	private Products productObjj;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Products getProductObjj() {
		return productObjj;
	}
	public void setProductObjj(Products productObjj) {
		this.productObjj = productObjj;
	}
	@Override
	public String toString() {
		return "Review [rid=" + rid + ", rating=" + rating + ", description=" + description + ", productObjj="
				+ productObjj + "]";
	}
	
	
	
	
	
}
