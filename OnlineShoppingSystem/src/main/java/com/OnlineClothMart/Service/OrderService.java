package com.OnlineClothMart.Service;

import java.text.DecimalFormat;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OnlineClothMart.Model.OrderedItems;
import com.OnlineClothMart.Model.PaymentType;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.dao.OrderDAO;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Transactional
	public void placeOrder(long userId, Products product,int quantity,long paymentTypeId) throws Exception {
		
		
			if(product.getQuantity()<=0 && quantity>=product.getQuantity())
			{
				throw new Exception("no stock available");
			}
			if(quantity<=0)
			{
				throw new Exception("cannot place order available need atleast one quantity");
			}
		
		
		DecimalFormat df=new DecimalFormat("0.00");
		double totalAmount=Double.parseDouble(df.format((product.getPrice()*quantity)-(quantity*(product.getOffer()*product.getPrice())/100)));
		
		this.orderDAO.placeorder(userId,product,quantity,paymentTypeId,totalAmount);
		
	}

	@Transactional
	public List<PaymentType> getPaymentTypes() {
		
		return this.orderDAO.getPaymentTypes();
	}

	@Transactional
	public List<OrderedItems> getOrders(long userId) {
		
		return this.orderDAO.getOrders(userId);
	}

	@Transactional
	public void deleteOrders(long orderId) {
		 this.orderDAO.deleteOrders(orderId);
		
	}
	
	@Transactional
	public List<OrderedItems> getOrderedItems()
	{
		return this.orderDAO.getOrderedItems();
	}
	@Transactional
	public OrderedItems updateOrderStatus(OrderedItems obj)
	{
		return this.orderDAO.updateOrderStatus(obj,"in progress delivered soon");
	}
	@Transactional
	public List<Products> getOrderedProducts()
	{
		return this.orderDAO.getOrderedProducts();
	}
	
	@Transactional
	public Products updateProduct(int id,OrderedItems obj)
	{
		return this.orderDAO.updateProduct(id, obj);
	}

}
