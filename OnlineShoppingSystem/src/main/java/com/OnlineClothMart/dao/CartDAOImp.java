package com.OnlineClothMart.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.ShoppingCart;
import com.OnlineClothMart.Model.ShoppingCartItem;
import com.OnlineClothMart.Model.User;









@Repository
public class CartDAOImp implements CartDAO {
	
	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
 
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	

		public Products addToCart(User uobj,int pid) {
			Session session = this.sessionFactory.getCurrentSession();
			 ShoppingCart shoppingCartObj=null;
			 long idd=uobj.getUserId();
			 Query q2=session.createQuery("from ShoppingCart where userIdCart=:id");
			 
			 q2.setParameter("id",idd);
			 
			shoppingCartObj=(ShoppingCart)q2.uniqueResult();
			User u=(User)session.get(User.class, idd);
			Products product=session.get(Products.class, pid);
	        ShoppingCartItem shopItemObj=new ShoppingCartItem();
	        long shopId=shoppingCartObj.getShoppingCartId();
	        shopItemObj.setQuantity(1);
	        double itemPrice=product.getPrice();
	        double offer=product.getOffer();
	        double itemTotal=itemPrice-((offer/100.0)*itemPrice);
	        shopItemObj.setItemTotal(itemTotal);
	        shopItemObj.setShoppingCart(shoppingCartObj);
	        shopItemObj.setCartProduct(product); 	        
	        session.save(shopItemObj);
	        updateTotalAmount(shopId);
			return null;
	}
		void updateTotalAmount(long shopId)
		{
			Session session = this.sessionFactory.getCurrentSession();
		Query q1=session.createQuery("select sum(itemTotal) from ShoppingCartItem where shoppingCartId=:shopId");
        q1.setParameter("shopId", shopId);
                                                                                             
        
        Double totalAmount=(Double)q1.uniqueResult();
        if(totalAmount==null)
        	totalAmount=0d;
        Query q3=session.createQuery("update ShoppingCart set cartTotal=:totalAmount where shoppingCartId=:shopId");
        q3.setParameter("totalAmount", totalAmount);
        q3.setParameter("shopId", shopId);
        int status=q3.executeUpdate();
		}
		
		public ShoppingCart getShoppingCart(int id)
		{
			Session session = this.sessionFactory.getCurrentSession();
			 Query q=session.createQuery("from ShoppingCart where userIdCart=:id");
		     q.setParameter("id", id);
		     ShoppingCart shopObj=(ShoppingCart)q.uniqueResult();
		     return shopObj;
		}
		
		public List<Products> getUserCartProducts(int id)
		{
			long idd=id;
			ShoppingCart shopCartObj=getShoppingCart(id);
			long shopId=shopCartObj.getShoppingCartId();
			Session session = this.sessionFactory.getCurrentSession();
			 Query q=session.createQuery("from ShoppingCartItem where shoppingCartId=:shopId");
		     q.setParameter("shopId", shopId);
		     
		     List<Products> productList=new ArrayList<Products>();
		     List<ShoppingCartItem> shopList=q.list();
		     for(ShoppingCartItem o:shopList)
		     {
		    	 productList.add(o.getCartProduct());
		     }
		     return productList;
		}
		
		public Products removeProductFromCart(User user,int pid)
		{
			Session session = sessionFactory.openSession();
	        Transaction tx=null;
	        try
	        {
	            tx=session.beginTransaction();
			
			 Products pobj=session.get(Products.class, pid);
		       long uid= user.getUserId();
			     ShoppingCart shopObj=getShoppingCart((int)uid);
		        long shopId=shopObj.getShoppingCartId();
		        double price=pobj.getPrice();
		    Query q1=session.createQuery("from ShoppingCartItem where shoppingCartId=:sId and pid=:pid");
			q1.setParameter("sId",shopId);
			q1.setParameter("pid",pid);
			
			ShoppingCartItem ob=(ShoppingCartItem)q1.uniqueResult();
			session.delete(ob);
			updateTotalAmount(shopId);
			tx.commit();
            session.close();
			
			
	        }
	        catch(Exception ex)
	        {
	            if(tx!=null)
	             {
	            	ex.printStackTrace();
	                 tx.rollback();
	                 
	             } 
	        }
			return null;
			
		}
		
		

}
