package com.OnlineClothMart.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ShoppingCart")
public class ShoppingCart implements Serializable{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shoppingCartId")
	long shoppingCartId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="userIdCart",referencedColumnName = "userId",unique=true)
	private User userCart;

	 @JsonIgnore
	 @OneToMany(mappedBy="shoppingCart")
	 private List<ShoppingCartItem> shoppingCartItem=new ArrayList<ShoppingCartItem>();
	 
	 @Column(name="cartTotal")
	 private double cartTotal;
	 
	 
	public ShoppingCart()
	{
		super();
	}
	public long getShoppingCartId()
	{
		return shoppingCartId;
	}
	public void setShoppingCartId(long shoppingCartId)
	{
		this.shoppingCartId=shoppingCartId;
	}
	public User getUserCart() 
	{
		return userCart;
	}
	public void setUserCart(User userCart) 
	{
		this.userCart = userCart;
	}
	
	public List<ShoppingCartItem> getShoppingCartItem()
	{
		return shoppingCartItem;
	}
	
	public void setShoppingCartItem(List<ShoppingCartItem> shoppingCartItem)
	{
		this.shoppingCartItem=shoppingCartItem;
	}
	public double getCartTotal()
	{
		return cartTotal;
	}
	public void setCartTotal(double cartTotal)
	{
		this.cartTotal=cartTotal;
	}
	
	@Override
	public String toString() {
		return "ShoppingCart [shoppingCartId=" + shoppingCartId + ", userCart=" + userCart + ", cartTotal=" + cartTotal
					+ "]";
		}
}
