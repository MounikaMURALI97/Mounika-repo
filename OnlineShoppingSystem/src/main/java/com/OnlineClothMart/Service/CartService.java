package com.OnlineClothMart.Service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.ShoppingCart;
import com.OnlineClothMart.Model.User;
import com.OnlineClothMart.dao.CartDAO;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Transactional
	public Products addToCart(User u,int pid)
	{
		return this.cartDAO.addToCart(u,pid);
	}
	@Transactional
	public ShoppingCart getShoppingCart(int id)
	{
		return this.cartDAO.getShoppingCart(id);
	}
	
	@Transactional
	public List<Products> getUserCartProducts(int id)
	{
		return this.cartDAO.getUserCartProducts(id);
	}
	@Transactional
	public Products removeProductFromCart(User user,int pid)
	{
		return this.cartDAO.removeProductFromCart(user,pid);
	}
	
	

}
