package com.OnlineClothMart.Model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PaymentType")
public class PaymentType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paymentTypeId")
	long paymentTypeId;
	
	@NotBlank
	@Column(name="paymentTypeName",unique=true)
	String paymentTypeName;
	
	@OneToMany(mappedBy="paymentIdType")
	@JsonIgnore
	private List<Orders> orderPaymentType;
	
	public PaymentType() {
		super();
	}
	
	
	public long getPaymentTypeId()
	{
		return paymentTypeId;
	}
	public void setPaymentTypeId(long paymentTypeId)
	{
		this.paymentTypeId=paymentTypeId;
	}
	
	public String getPaymentTypeName()
	{
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName)
	{
		this.paymentTypeName=paymentTypeName;
	}
	@JsonIgnore
	public List<Orders> getOrderPaymentType()
	{
		return orderPaymentType;
	}
	@JsonIgnore
	public void setOrderPaymentType(List<Orders> orderPaymentType)
	{
		this.orderPaymentType=orderPaymentType;
	}
	
	 @Override
		public String toString() {
			return "PaymentType [paymentTypeId=" + paymentTypeId + ", paymentTypeName=" + paymentTypeName + "]";
			}
	
	

}
