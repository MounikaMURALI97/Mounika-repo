package com.OnlineClothMart.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class Products implements Serializable{
	@Id
	@GeneratedValue
	private int pid;
	private String pname;
	private String availabilbity;
	private double price;
	private String description;
	private double offer;	
	@Lob
	private byte[] image;
	private int quantity;
	 @ManyToOne
	 @JoinColumn(name="cid")
	 
	 private Category categoryobj;

	@OneToMany(mappedBy="productObjj")
	@JsonIgnore
	private List<Review> reviewList=new ArrayList<Review>();
	
	@OneToMany(mappedBy="cartProduct")    
	@JsonIgnore
	private List<ShoppingCartItem>  shoppingCartItems=new ArrayList<ShoppingCartItem>();  
	
	@OneToMany(mappedBy="orderedProducts") 
	@JsonIgnore
	private List<OrderedItems>  orderedItems=new ArrayList<OrderedItems>();  
	                                                                                                
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	

	public String getAvailabilbity() {
		return availabilbity;
	}

	public void setAvailabilbity(String availabilbity) {
		this.availabilbity = availabilbity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getOffer() {
		return offer;
	}

	public void setOffer(double offer) {
		this.offer = offer;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	public Category getCategory() {
		return categoryobj;
	}
	
	public void setCategory(Category categoryobj)
	{
		this.categoryobj=categoryobj;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}
	
	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
	
	public List<OrderedItems> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<OrderedItems> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", availabilbity=" + availabilbity + ", price=" + price
				+ ", description=" + description + ", offer=" + offer + ", image=" + Arrays.toString(image)+ ",quantity="+quantity+"]";
	}

	
	
	
	
	
	

}
