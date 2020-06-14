package com.OnlineClothMart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.OnlineClothMart.Model.Address;
import com.OnlineClothMart.Model.User;

import com.OnlineClothMart.Service.UserService;

@RestController

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public User create(@RequestBody User user)throws Exception {
		
		 return userService.registerUser(user);
	}

	@RequestMapping("/login")
	public User login(@RequestBody User user) throws Exception	{
		
		String gmailId=user.getGmailId();
		String password=user.getPassword();
		User userobj=null;		
		if(gmailId!=null && password!=null)
		{
			userobj=userService.findUser( gmailId, password);
			
		}
		if(userobj==null)
		{
			throw new Exception("Invalid Login! Wrong username/password");
		}		
		
		return userobj;
	}
	
	@RequestMapping("/adminlogin")
	public User adminLogin(@RequestBody User user) throws Exception	{
		
		String gmailId=user.getGmailId();
		String password=user.getPassword();
		User userobj=null;		
		if(gmailId!=null && password!=null)
		{
			userobj=userService.findAdmin( gmailId, password);
			
		}
		if(userobj==null)
		{
			
			throw new Exception("Invalid Login! Wrong username/password");
		}		
		
		return userobj;
	}
	
	@PostMapping("/updateUser")
	public void updateUserDetails(@RequestBody User user) throws Exception
	{
		this.userService.updateUserDetails(user);
	}
	@GetMapping("/users")
	 public List<User> getUsers()  {

	      return  this.userService.getUsers();
	   }
	
	@GetMapping("/user/{userId}")
	 public User findUserById(@PathVariable("userId") long userId)  {
		   
	     User user = this.userService.findUserById(userId);
	     
	      return user;
	   }
	
	@GetMapping("/deleteuser/{userId}")
	public void deleteUser(@PathVariable("userId")long userId)
	{
		
		this.userService.deleteUser(userId);
	}
   	
	@PostMapping("/useraddress")
	public void updateUserAddressDetails(@RequestBody Address address) throws Exception
	{
		this.userService.updateUserAddressDetails(address);
	}
	
	@PostMapping("/saveaddress/{userId}")	
	public void saveUserAddressDetails(@PathVariable("userId") long userId,@RequestBody Address address) throws Exception
	{
		
		
		this.userService.saveUserAddressDetails(userId,address);
	}
	
	@GetMapping("/address/{userId}")	
	 public Address findAddressById(@PathVariable("userId") long userId) throws Exception {
		Address address= this.userService.findAddressById(userId);
	   
	    return address;
	 }
}
