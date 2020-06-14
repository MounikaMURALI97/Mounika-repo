package com.OnlineClothMart.dao;


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
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.Review;






@Repository
public class ProductDAOImp implements ProductDAO{

	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
 
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	public List<Products> findProductByName(String productNameValue) {
		
		Session session = this.sessionFactory.getCurrentSession();
		String hql="FROM Products  WHERE pname like :searchvalue";	
		Query query = session.createQuery(hql);			
		query.setString("searchvalue", "%"+productNameValue+"%");
	
		List<Products> productList=query.list();
		return productList;
	}
	
	public List<Products> findProductByCategory(Integer cid) {
		
		Session session = this.sessionFactory.getCurrentSession();
		String hql="FROM Products where cid=:cid";	
		Query query = session.createQuery(hql);			
		query.setParameter("cid", cid);			
		List<Products> productList=query.list();
		
		
		return productList;
	}
	

	public List<Products> getProducts() {
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM Products");
		List<Products> productsList = query.list();
		
		return productsList;
	}
	
	
	public Products getProductById(int pid) {
		Session session=this.sessionFactory.getCurrentSession();
		Products product=(Products)session.get(Products.class, pid);
		
		return product;
	}
	
	public void updateProductImage(byte[] bs,int pid) {
		Session session = this.sessionFactory.getCurrentSession();
		Products product = (Products) session.get(Products.class, pid);
		product.setImage(bs);
		session.saveOrUpdate(product);		
	}
	
	public void saveProduct(Products product) {
		Session session = this.sessionFactory.getCurrentSession();
		if(product.getQuantity()>0)
		{
			product.setAvailabilbity("yes");
		}
		else
		{
			product.setAvailabilbity("no");
		}
		
		session.saveOrUpdate(product);
		
	}
	
	public void deleteProduct(int pid)
	{
		
		Session session = sessionFactory.openSession();
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            Products product = (Products) session.get(Products.class, pid);
            
            String hql1=("delete OrderedItems where orderedProducts=:product");		
			Query query1=session.createQuery(hql1);
			query1.setParameter("product", product);
			query1.executeUpdate();
			
			
			String hql3=("delete ShoppingCartItem where cartProduct=:product");		
			Query query3=session.createQuery(hql3);
			query3.setParameter("product", product);
			query3.executeUpdate();
			
			
            String hql2=("from Review where productObjj=: product");    		
    		Query query2=session.createQuery(hql2);
    		query2.setParameter("product", product);
    		List<Review> reviewList=query2.list();
    		if(reviewList.size()>0)
    		{
    			for(Review review:reviewList)
    			{
    				review.setProductObjj(null);
    				session.saveOrUpdate(review);
    			}
    			
    		}
    					
			product.setCategory(null);
			String hql4=("delete Products where pid=:pid");		
			Query query4=session.createQuery(hql4);
			query4.setParameter("pid", pid);
			query4.executeUpdate();   		
			
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
	
	public Products getProductDetail(int n) {
		Session session = this.sessionFactory.getCurrentSession();
        Query q=session.createQuery("from Products where pid=:n");
        q.setParameter("n", n);
        Products pobj=(Products)q.uniqueResult();
		return pobj;
	}
	
	public List<Review> getProductReview(int id)
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query q=session.createQuery("from Review where pid=:n");
        q.setParameter("n", id);
        List<Review> review=q.list();
		return review;
				
	}
	
	public Review addReview(Review review,int pid,int userId)
	{
		int result=0;
		Session session=this.sessionFactory.getCurrentSession();
		Query q=session.createQuery("from OrderedItems where pid=:n");
        q.setParameter("n",pid);
        List<OrderedItems> itemObj=q.list();
        Products pobj=session.get(Products.class, pid);
        for(OrderedItems o:itemObj)
        {
        	
        	Orders order=o.getOrder();
        	long id=order.getUserIdPayment().getUserId();
        	if(userId==id)
        	{
        		result=1;
        		review.setProductObjj(pobj);
        		session.save(review);
        		break;
        	}
        }
        return review;
	}

}
