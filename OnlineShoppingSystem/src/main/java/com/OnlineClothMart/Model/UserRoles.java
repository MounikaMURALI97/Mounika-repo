package com.OnlineClothMart.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="UserRoles")
public class UserRoles implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="userId")
	private User userIdRole;
	
	@Id
	@ManyToOne
	@JoinColumn(name="roleId")
	private Roles userRoleId;
		
	
	public UserRoles()
	{
		super();
	}
	public User getUserIdRole()
	{
		return userIdRole;
	}
	public void setUserIdRole(User userIdRole)
	{
		this.userIdRole=userIdRole;
	}
	
	public Roles getUserRoleId()
	{
		return userRoleId;
	}
	public void setUserRoleId(Roles userRoleId)
	{
		this.userRoleId=userRoleId;
	}
	
	 @Override
		public String toString() {
			return "UserRoles [userIdRole=" + userIdRole + ", userRoleId=" + userRoleId + "]";
			}
	
	
}
