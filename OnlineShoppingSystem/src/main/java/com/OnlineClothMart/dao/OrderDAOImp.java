package com.OnlineClothMart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.OnlineClothMart.Model.OrderedItems;
import com.OnlineClothMart.Model.Orders;
import com.OnlineClothMart.Model.PaymentType;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.User;

@Repository
public class OrderDAOImp implements OrderDAO{
	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
 
	List<Products> orderedProdList=null;
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public void placeorder(long userId, Products product,int quantity,long paymentTypeId,double totalAmount) throws Exception{
		Session session = sessionFactory.openSession();
		Orders order=new Orders();
		OrderedItems oi=new OrderedItems();
        Transaction tx=null;
        User user = (User) session.get(User.class, userId);  
        if(user.getAddress()==null)
        {
        	 throw new Exception("Address not found");
        }
        try
        {
            tx=session.beginTransaction();
                             
    		order.setUserIdPayment(user);
    		order.setOrderDate(new Date());    		
    		PaymentType pay = (PaymentType) session.get(PaymentType.class,paymentTypeId );    		
    		order.setPaymentIdType(pay);
    		order.setPaymentStatus("success");
    		order.setOrderStatus("order placed");
    		order.setTotalAmount(totalAmount);    		
    		oi.setOrderedProducts(product);
    		oi.setOrderedQuantity(quantity);
    		oi.setOrder(order);
    		oi.setSubTotal(totalAmount);
    		
    		product.setQuantity(product.getQuantity()-quantity);
    		session.update(product);
    		session.save(order);
    		session.save(oi);
            tx.commit();
            session.close();     
        }catch(Exception ex)
        {
            if(tx!=null)
             {
                 tx.rollback();
                 
             } 
        }

	}

	
	public List<PaymentType> getPaymentTypes() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql="FROM PaymentType ";	
		Query query = session.createQuery(hql);	
		List<PaymentType> paymentTypesList=query.list();
		return paymentTypesList;
	}

	
	public List<OrderedItems> getOrders(long userId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql="FROM Orders where userId=:userId";	
		Query query = session.createQuery(hql);	
		query.setLong("userId",userId);
		List<Orders> OrdersList=query.list();
		List<OrderedItems> OrderedItemsList=new ArrayList<OrderedItems>();
		for(Orders order:OrdersList)
		{
			String hql1="FROM OrderedItems where orderId=:orderId";
			Query query1 = session.createQuery(hql1);	
			query1.setLong("orderId",order.getOrderId());
			OrderedItemsList.addAll(query1.list());
		}		
		return OrderedItemsList;
	}

	public void deleteOrders(long orderId) {
		
		Session session = sessionFactory.openSession();
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            Orders orders = (Orders) session.get(Orders.class, orderId);
            String hql=("FROM OrderedItems where order=:orderId");		
			Query query=session.createQuery(hql);
			query.setParameter("orderId", orders);
            List<OrderedItems> orderedItemsList=query.list();
            if(orderedItemsList.size()>0)
            {
            	for(OrderedItems orderedItems:orderedItemsList)
            	{
            		Products product=(Products)session.get(Products.class, orderedItems.getOrderedProducts().getPid());                   
                    
            		product.setQuantity(product.getQuantity()+orderedItems.getOrderedQuantity());
                    product.setAvailabilbity("yes");
                    session.save(product);
            	}
            }
            
            String hql1=("delete OrderedItems where order=:orderId");		
			Query query1=session.createQuery(hql1);
			query1.setParameter("orderId", orders);
			query1.executeUpdate();
			
			session.delete(orders);			
    		tx.commit();
            session.close();
            
        }catch(Exception ex)
        {
            if(tx!=null)
             {
            	ex.printStackTrace();
                 tx.rollback();
                 
             } 
        }
		
	}
	
	
	public List<OrderedItems> getOrderedItems()
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query q=session.createQuery("from OrderedItems");
		List<OrderedItems> itemsList=q.list();
		orderedProdList=new ArrayList<Products>();
		for(OrderedItems o:itemsList)
		{
			orderedProdList.add(o.getOrderedProducts());
		}
		return itemsList;
	}
	
	public OrderedItems updateOrderStatus(OrderedItems obj,String s)
	{
	
		Session session=this.sessionFactory.getCurrentSession();
		Orders orderObj=obj.getOrder();
		if(s.equals("order canceled"))
		{
			orderObj.setOrderStatus(s);
			orderObj.setPaymentStatus("Refund");
		}
		else
			orderObj.setOrderStatus(s);
		session.update(orderObj);
		return null;
		
	}
	public List<Products> getOrderedProducts()
	{
		return orderedProdList;
	}
	public Products updateProduct(int id,OrderedItems obj)
	{
		updateOrderStatus(obj,"order canceled");
		int q=obj.getOrderedQuantity();
		Session session=this.sessionFactory.getCurrentSession();		
		Products prodObj=session.get(Products.class, id);
	    int quantity=prodObj.getQuantity()+q;	    
	    prodObj.setQuantity(quantity);
	    session.update(prodObj);
	    obj.setOrderedQuantity(0);
	    session.update(obj);
	    
	    
	    return null;
	}
	


}
