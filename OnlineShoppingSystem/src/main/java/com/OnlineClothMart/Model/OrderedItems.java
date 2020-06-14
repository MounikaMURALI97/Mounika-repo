package com.OnlineClothMart.Model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="OrderedItems")
public class OrderedItems implements Serializable{
	
	 @Id 
	 @ManyToOne
	 @JoinColumn(name="pid",referencedColumnName = "pid")
	 private Products orderedProducts;
	 
	 @Id
	 @ManyToOne
	 @JoinColumn(name="orderId",referencedColumnName = "orderId")
	 private Orders order;
	 
	 @NotNull
	 @Column(name="orderedQuantity")
	 int orderedQuantity;
	 
	 @NotNull
	 @Column(name="subTotal")
	 private double subTotal;
	 public OrderedItems()
	 {
			super();
	 }
	 
	 public Products getOrderedProducts()
	 {
		 return orderedProducts;
	 }
	 public void setOrderedProducts(Products orderedProducts)
	 {
		 this.orderedProducts=orderedProducts;
	 }
	  public Orders getOrder()
	  {
		  return order;
	  }
	  public void setOrder(Orders order)
	  {
		  this.order=order;
	  }
	  
	  public int getOrderedQuantity()
	  {
		  return orderedQuantity;
	  }
	  public void setOrderedQuantity(int orderedQuantity)
	  {
		  this.orderedQuantity=orderedQuantity;
	  }
	  
	  public double getSubTotal()
	  {
		  return subTotal;
	  }
	  public void setSubTotal(double subTotal)
	  {
		  this.subTotal=subTotal;
	  }
	  @Override
		public String toString() {
			return "OrderedItems [orderedProducts=" + orderedProducts + ", order=" + order + ", orderedQuantity=" + orderedQuantity + ", subTotal=" + subTotal
						+ "]";
			}
	  
}
