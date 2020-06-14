package com.OnlineClothMart.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="addressId")
	long addressId;
	
	@NotBlank
	@Column(name="street")
	String street;
	
	@NotBlank
	@Column(name="district")
	String district;
	
	@NotBlank
	@Column(name="state")
	String state;
	
	@NotBlank
	@Column(name="country")
	String country;
	
	@NotNull
	@Column(name="pinCode")
	int pinCode;
	
	@OneToOne
	@JoinColumn(name="userId",referencedColumnName = "userId",unique=true)
	@NotNull
	private User userAddress;
	
	
	
	
	public Address()
	{
		super();
	}
	public Address(long addressId,String street,String district,String state,String country,int pinCode,User userAddress)
	{
		this.addressId=addressId;
		this.street=street;
		this.district=district;
		this.state=state;
		this.country=country;
		this.pinCode=pinCode;
		this.userAddress=userAddress;
	}
	
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long  addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String  street) {
		this.street = street;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String  district) {
		this.district = district;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String  country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String  state) {
		this.state = state;
	}
	public int getPinCode()
	{
		return pinCode;
	}
	public void setPinCode(int pinCode)
	{
		this.pinCode=pinCode;
	}
	
	public User getUser() 
	{
		return userAddress;
	}
	public void setUser(User userAddress) 
	{
		this.userAddress = userAddress;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", district=" + district + ", state=" + state
					+ ", country=" + country + ", pinCode=" + pinCode + ", userAddress=" + userAddress
					+ "]";
		}
	
}
