package com.OnlineClothMart.Service;


import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OnlineClothMart.Model.Address;
import com.OnlineClothMart.Model.User;
import com.OnlineClothMart.dao.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	
	@Transactional
	public User registerUser(User userObj)
	{
		User user1 = userDAO.registerUser(userObj);
	    return user1;
	}
	
	@Transactional
	
	public User findUser(String gmailId,String password)
		 {

			User userFromList = userDAO.findUser(gmailId,password);
			
		return userFromList;

	}

	@Transactional
	public void updateUserDetails(User user) {
		this.userDAO.updateUserDetails(user);
		
	}

	@Transactional
	public User findUserById(long userId) {
		
		User user= this.userDAO.findUserById(userId);
		
		return user;
	}

	@Transactional
	public void updateUserAddressDetails(Address address) {
		
		this.userDAO.updateUserAddressDetails(address);
	}
	
	@Transactional
	public Address findAddressById(long userId) {
		
		Address address= this.userDAO.findAddressById(userId);
		User user=address.getUser();
		Address address1=new Address(address.getAddressId(),address.getStreet(),address.getDistrict(),address.getState(),address.getCountry(),address.getPinCode(),user);
		
		return address1;
	}

	@Transactional
	public void saveUserAddressDetails(long userId,Address address) {
		this.userDAO.saveUserAddressDetails(userId,address);
		
	}
	
	@Transactional
	public User findAdmin(String gmailId, String password) {
		
		return this.userDAO.findAdmin(gmailId,password);
	}

	@Transactional
	public List<User> getUsers() {
		
		return  this.userDAO.getUsers();
	}

	@Transactional
	public void deleteUser(long userId) {
		this.userDAO.deleteUser(userId);
		
	}

	

}
