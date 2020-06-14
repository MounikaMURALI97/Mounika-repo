package com.OnlineClothMart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.OnlineClothMart.Model.Category;
import com.OnlineClothMart.Model.Products;


@Repository
public class CategoryDAOImp implements CategoryDAO{

	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
 
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	public List<Category> getCategory() {

		Session session1 = this.sessionFactory.getCurrentSession();	
		Query query6 = session1.createQuery("FROM Category");
		List<Category> categoryList = query6.list();
		return categoryList;
	}
	
	public void saveCategory(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		if(category.getCname()!=null)
		{			
			session.saveOrUpdate(category);
		}
		
	}
	
	public Category getCategoryById(int cid) {
		Session session=this.sessionFactory.getCurrentSession();
		Category category=(Category)session.get(Category.class, cid);
		return category;
	}
	
	public void deleteCategory(int cid) {
		Session session = sessionFactory.openSession();
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            Category category = (Category) session.get(Category.class, cid);  
            String hql2=("from Products where categoryobj=: category");    		
    		Query query2=session.createQuery(hql2);
    		query2.setParameter("category", category);
    		List<Products> productsList=query2.list();
    		if(productsList.size()>0)
    		{
    			for(Products product:productsList)
    			{
    				product.setCategory(null);
    				session.saveOrUpdate(product);
    			}
    			
    		}
    		session.delete(category);
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
		
	

}
