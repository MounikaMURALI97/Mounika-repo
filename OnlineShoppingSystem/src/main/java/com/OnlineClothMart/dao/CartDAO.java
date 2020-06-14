package com.OnlineClothMart.dao;

import java.util.List;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.ShoppingCart;
import com.OnlineClothMart.Model.User;


public interface CartDAO {
	Products addToCart(User u,int pid);
	ShoppingCart getShoppingCart(int id);
	List<Products> getUserCartProducts(int id);
	Products removeProductFromCart(User user,int pid);
	
}
