package com.OnlineClothMart.Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="User")
public class User {
	
	@Id	
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long userId;
	
    @NotBlank
	@Column(name="gmailId",unique=true)	
	String gmailId;
	
	@NotBlank
   	@Column(name="password")
    String password;

    @NotBlank
	@Column(name="firstName")
    String firstName;
    
    @NotBlank
   	@Column(name="lastName")
    String lastName;
 
    
   	@Column(name="gender")
    String gender;
   
    
    @NotNull
   	@Column(name="mobileNumber")
	private String mobileNumber;
    
    @OneToOne(mappedBy="userAddress",fetch=FetchType.LAZY)
    @JsonIgnore
    private Address address;

    @OneToMany(mappedBy="userIdRole",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<UserRoles> userRolesrole=new ArrayList<UserRoles>();
    
    @OneToOne(mappedBy="userCart",fetch=FetchType.LAZY)
    @JsonIgnore
    private ShoppingCart userShoppingCart;
    
    @OneToMany(mappedBy="userIdPayment",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Orders> userOrder;

	public User() {
		super();
	}
	public User(long userId,String gmailId,String password,String firstName,String lastName,String gender,String mobileNumber,Address address) {
		this.userId=userId;
		this.gmailId=gmailId;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;		
		this.gender=gender;		
		this.setMobileNumber(mobileNumber);
		this.address=address;
		
		
	}
	
	public User(long userId,String gmailId,String password) {
		this.userId=userId;
		this.gmailId=gmailId;
		this.password=password;	
		
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long  userId) {
		this.userId = userId;
	}
	public String getGmailId() {
		return gmailId;
	}
	public void setGmailId(String  gmailId) {
		this.gmailId = gmailId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String LastName) {
		this.lastName = LastName;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String Gender) {
		this.gender = Gender;
	}
	
	
	@JsonIgnore
	public Address getAddress() {
		return address;
	}
	@JsonIgnore
	public void setAddress(Address address) {
		this.address = address;
	}
	@JsonIgnore
	public List<UserRoles> getUserRolesRole()
	{
		return userRolesrole;
	}
	@JsonIgnore
	public void setUserRolesRole(List<UserRoles> userRolesrole)
	{
		this.userRolesrole=userRolesrole;
	}
	@JsonIgnore
     public ShoppingCart getUserShoppingCart()
	    {
	    	return userShoppingCart;
	    }
	 public void setUserShoppingCart(ShoppingCart userShoppingCart)
	    {
	    	this.userShoppingCart=userShoppingCart;
	    }
	    
	 @JsonIgnore
	 public List<Orders> getUserOrder()
	   	{
	    	return userOrder;
	    }
	 @JsonIgnore
	 public void setUserOrder(List<Orders> userOrder)
	    {
	    	this.userOrder=userOrder;
	    }
	 
	 public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
	 
	@Override
	public String toString() {
		return "User [userId=" + userId + ", gmailId=" + gmailId + ", password=" + password + ", firstName=" + firstName
					+ ", lastName=" + lastName + ", gender=" + gender + ", mobileNumber=" + getMobileNumber() + "]";
		}
	
	
	
	

}
