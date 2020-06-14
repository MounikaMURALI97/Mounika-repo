package com.OnlineClothMart.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ShoppingCartItem")
public class ShoppingCartItem implements Serializable{

	 @Id 
	 @ManyToOne
	 @JsonIgnore
	 @JoinColumn(name="pid",referencedColumnName = "pid")
	 private Products cartProduct;
	 
	 @Id
	 @JsonIgnore
	 @ManyToOne
	 @JoinColumn(name="shoppingCartId",referencedColumnName = "shoppingCartId")
	 private ShoppingCart shoppingCart;
	 
	 @NotNull
	 @Column(name="quantity")
	 int quantity;
	 
	 @Column(name="itemTotal")
	 private double itemTotal;
	 
	
	 
	
	 public Products getCartProduct()
	 {
		 return cartProduct;
	 }
	 public void setCartProduct(Products cartProduct)
	 {
		 this.cartProduct=cartProduct;
	 }
	  public ShoppingCart getShoppingCart()
	  {
		  return shoppingCart;
	  }
	  public void setShoppingCart(ShoppingCart shoppingCart)
	  {
		  this.shoppingCart=shoppingCart;
	  }
	  
	  public int getQuantity()
	  {
		  return quantity;
	  }
	  public void setQuantity(int quantity)
	  {
		  this.quantity=quantity;
	  }
	  
	  public double getItemTotal()
	  {
		  return itemTotal;
	  }
	  public void setItemTotal(double itemTotal)
	  {
		  this.itemTotal=itemTotal;
	  }
	  
	  @Override
		public String toString() {
			return "ShoppingCartItem [cartProduct=" + cartProduct + ", shoppingCart=" + shoppingCart + ", quantity=" + quantity + ", itemTotal=" + itemTotal
						+ "]";
			}
 
}
