package com.OnlineClothMart.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.OnlineClothMart.Model.OrderedItems;
import com.OnlineClothMart.Model.PaymentType;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order/{userId}/orderQuantity/{quantity}/paymentMethod/{paymentTypeId}")	
	public void placeOrder(@PathVariable("userId")long userId,@RequestBody Products product,@PathVariable("quantity")int quantity,@PathVariable("paymentTypeId")long paymentTypeId) throws Exception
	{
		this.orderService.placeOrder(userId,product,quantity,paymentTypeId);
	}
	
	@GetMapping("/paymentypes")
	public List<PaymentType> getPaymentTypes()
	{
		
		return this.orderService.getPaymentTypes();
	}
	
	@GetMapping("/orders/{userId}")
	@CrossOrigin("http://localhost:4200/orders")
	public List<OrderedItems> getOrders(@PathVariable("userId")long userId)
	{
		
		return this.orderService.getOrders(userId);
	}
	
	@GetMapping("/deleteOrder/{orderId}")
	@CrossOrigin("http://localhost:4200/orders")
	public void deleteOrders(@PathVariable("orderId")long orderId)
	{
		
		 this.orderService.deleteOrders(orderId);
	}

	
	
	@GetMapping("/getOrderedItems")
	List<OrderedItems> getOrderedItems()
	{
		 return this.orderService.getOrderedItems();
	}
	
	@PostMapping("/updateOrderStatus")
	OrderedItems updateOrderStatus(@RequestBody OrderedItems obj)
	{
		return this.orderService.updateOrderStatus(obj);
		
	}
	
	
    @GetMapping("/getOrderedProducts")
	List<Products> getOrderedProducts()
	{
		return this.orderService.getOrderedProducts();
	}
	
    
    @PostMapping("/updateProduct/{id}")
	Products updateProduct(@RequestBody OrderedItems obj,@PathVariable("id") int id)
	{
		return this.orderService.updateProduct(id,obj);
	}
}
