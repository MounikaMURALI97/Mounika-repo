package com.OnlineClothMart.dao;

import java.util.List;

import com.OnlineClothMart.Model.OrderedItems;
import com.OnlineClothMart.Model.PaymentType;
import com.OnlineClothMart.Model.Products;

public interface OrderDAO {

	void placeorder(long userId, Products product,int quantity,long paymentTypeId,double totalAmount) throws Exception;

	List<PaymentType> getPaymentTypes();

	List<OrderedItems> getOrders(long userId);

	void deleteOrders(long orderId);
	
	List<OrderedItems> getOrderedItems();
	OrderedItems updateOrderStatus(OrderedItems obj,String s);
	List<Products> getOrderedProducts();
	Products updateProduct(int id,OrderedItems obj);

}
