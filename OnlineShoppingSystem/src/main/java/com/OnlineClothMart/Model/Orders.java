package com.OnlineClothMart.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Orders")

public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderId")
	private long orderId;
	
	@ManyToOne
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private User userIdPayment;	

	@ManyToOne
	@JoinColumn(name="paymentTypeId",referencedColumnName = "paymentTypeId")
	private PaymentType paymentIdType;
	
	@Column(name="totalAmount")
	private double totalAmount;
	
	@Column(name="orderDate")
	private Date orderDate;
	
	@Column(name="paymentStatus")
	private String paymentStatus;
	
	@Column(name="orderStatus")
	private String orderStatus;
	
	@OneToMany(mappedBy="order",fetch=FetchType.LAZY)
	@JsonIgnore
	 private List<OrderedItems> orderOrderedItems=new ArrayList<OrderedItems>();
	
	
	public Orders()
	{
		super();
	}
	public long getOrderId()
	{
		return orderId;
	}
	public void setOrderId(long orderId)
	{
		this.orderId=orderId;
	}
	public User getUserIdPayment() 
	{
		return userIdPayment;
	}
	public void setUserIdPayment(User userIdPayment) 
	{
		this.userIdPayment = userIdPayment;
	}
	public PaymentType getPaymentIdType()
	{
		return paymentIdType;
	}
	public void setPaymentIdType(PaymentType paymentIdType)
	{
		this.paymentIdType=paymentIdType;
	}
	@JsonIgnore
	public List<OrderedItems> getOrderOrderedItems()
	{
		return orderOrderedItems;
	}
	@JsonIgnore
	public void setOrderOrderedItems(List<OrderedItems> orderOrderedItems)
	{
		this.orderOrderedItems=orderOrderedItems;
	}
	public double getTotalAmount()
	{
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount)
	{
		this.totalAmount=totalAmount;
	}	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getPaymentStatus()
	{
		return paymentStatus;
	}
	
	public void setPaymentStatus(String paymentStatus)
	{
		this.paymentStatus=paymentStatus;
	}
	
	public String getOrderStatus()
	{
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus)
	{
		this.orderStatus=orderStatus;
	}
	
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", userIdPayment=" + userIdPayment + ", paymentIdType=" + paymentIdType + ", totalAmount=" + totalAmount
					+ ",orderDate="+orderDate+",paymentStatus="+paymentStatus+",orderStatus="+orderStatus+"]";
		}

}
