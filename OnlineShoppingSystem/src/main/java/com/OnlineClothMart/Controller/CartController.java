package com.OnlineClothMart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.ShoppingCart;
import com.OnlineClothMart.Model.User;
import com.OnlineClothMart.Service.CartService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@PostMapping(value="/addToCart/{id}")
	Products addToCart(@RequestBody User user,@PathVariable("id") int pid) {
		Products pobj=null;
		cartService.addToCart(user,pid);
		return pobj;
	}
	
	
	@GetMapping("/getUserCartAmount/{id}")
	ShoppingCart getShoppingCart(@PathVariable("id") int id)
	{
		return cartService.getShoppingCart(id);
	}
	
	
	@GetMapping("/getUserCartProducts/{id}")
	List<Products> getUserCartProducts(@PathVariable("id") int id)
	{
		return cartService.getUserCartProducts(id);
	}
	
	
	@PostMapping("/removeProductFromCart/{id}")
	Products removeProductFromCart(@RequestBody User u,@PathVariable("id") int id)
	{
		return cartService.removeProductFromCart(u,id);
	}
	
	
	
	

}
