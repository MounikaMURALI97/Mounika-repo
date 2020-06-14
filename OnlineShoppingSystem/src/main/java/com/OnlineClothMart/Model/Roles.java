package com.OnlineClothMart.Model;


import java.util.ArrayList;
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
@Table(name="Roles")
public class Roles {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleId")
	long roleId;
	
	@NotBlank
	@Column(name="roleName")
	String roleName;
	
	@OneToMany(mappedBy="userRoleId")
	@JsonIgnore
	private List<UserRoles> userRoles=new ArrayList<UserRoles>();
	
	
	
	public Roles()
	{
		super();
	}
	
	public long getRoleId()
	{
		return roleId;
	}
	public void setRoleId(long roleId)
	{
		this.roleId=roleId;
	}
	public String getRoleName()
	{
		return roleName;
	}
	public void setRoleName(String roleName)
	{
		this.roleName=roleName;
	}
	
	@JsonIgnore
	public List<UserRoles> getUserRoles() 
	{
		return userRoles;
	}
	@JsonIgnore
	public void setUserRoles(List<UserRoles> userRoles) 
	{
		this.userRoles = userRoles;
	}

	 @Override
		public String toString() {
			return "Roles [roleId=" + roleId + ", roleName=" + roleName + ", userRoles=" + userRoles +"]";
			}
	

}
