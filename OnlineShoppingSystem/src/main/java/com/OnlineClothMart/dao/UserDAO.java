package com.OnlineClothMart.dao;

import java.util.List;
import com.OnlineClothMart.Model.Address;
import com.OnlineClothMart.Model.User;

public interface UserDAO {
	
	public User registerUser(User user);

	public User findUser(String gmailId, String password);

	public void updateUserDetails(User user);

	public User findUserById(long userId);

	public void updateUserAddressDetails(Address address);

	public Address findAddressById(long userId);

	public void saveUserAddressDetails(long userId,Address address);

	public User findAdmin(String gmailId, String password);

	public List<User> getUsers();

	public void deleteUser(long userId);

}
