package com.OnlineClothMart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.OnlineClothMart.Model.Address;
import com.OnlineClothMart.Model.Orders;
import com.OnlineClothMart.Model.Roles;
import com.OnlineClothMart.Model.ShoppingCart;
import com.OnlineClothMart.Model.User;
import com.OnlineClothMart.Model.UserRoles;





@Repository
public class UserDAOImp implements UserDAO{
	
	@Autowired
    @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
 
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public User registerUser(User user) {
		Session session = this.sessionFactory.getCurrentSession(); 
      
        long v=2;
        UserRoles userRolesobj=new UserRoles();
        Roles rolobj=(Roles)session.get(Roles.class, v) ;
        userRolesobj.setUserRoleId(rolobj);
        userRolesobj.setUserIdRole(user);        
        user.getUserRolesRole().add(userRolesobj);       
        User user1=null;
			try {
				 session.save(user);			        
			     session.save(userRolesobj);
			     createUserCart(user);
			     user1=session.get(User.class, user.getUserId());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
        return user1;
	}
	
	void createUserCart(User user)
	{
		Session session = this.sessionFactory.getCurrentSession();
		ShoppingCart shoppingCartObj=new ShoppingCart();
		 shoppingCartObj.setUserCart(user);
		 shoppingCartObj.setCartTotal(0);
		 session.save(shoppingCartObj);
	}
	
	@SuppressWarnings("unchecked")
	public User findUser(String gmailId,String password) {
		
		Session session = this.sessionFactory.getCurrentSession();		
		String hql="SELECT user FROM User user join UserRoles role on user.userId=role.userIdRole where role.userRoleId=2 AND user.gmailId=:gmailIdsearch AND user.password=:passwordsearch";	
		Query query = session.createQuery(hql);			
		query.setString("gmailIdsearch", gmailId);
		query.setString("passwordsearch", password);
		User user=(User)query.uniqueResult();	
		return user;
	}

	
	public User findByUserName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql="FROM User  WHERE gmailId=:gmailIdsearch AND password=:passwordsearch";	
		Query query = session.createQuery(hql);			
		query.setString("gmailIdsearch", username);		
		User user=(User)query.uniqueResult();		
		return user;
	}

	
	public void updateUserDetails(User user) {
		Session session = sessionFactory.openSession();
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            session.update(user);
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

	
	public User findUserById(long userId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		 User user = (User) session.get(User.class, userId);
		return user;
		
	}

	
	public void updateUserAddressDetails(Address address) {
		Session session = sessionFactory.openSession();
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();                   
            session.update(address);
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

	
	public Address findAddressById(long userId) {
		Session session = this.sessionFactory.getCurrentSession();
		String hql="FROM Address  WHERE userId=:userId";		
		Query query = session.createQuery(hql);		
		query.setLong("userId", userId);		
		Address address=(Address)query.uniqueResult();
		return address;
			
	}

	
	public void saveUserAddressDetails(long userId,Address address) {
		Session session = sessionFactory.openSession();
		
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            User user = (User) session.get(User.class, userId);            
            Address address1=new Address();
            address1.setStreet(address.getStreet());
            address1.setDistrict(address.getDistrict());
            address1.setState(address.getState());
            address1.setCountry(address.getCountry());
            address1.setPinCode(address.getPinCode());            
            address1.setUser(user);
            session.save(address1);
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

	public User findAdmin(String gmailId, String password) {
		Session session = this.sessionFactory.getCurrentSession();		
		String hql="SELECT user FROM User user join UserRoles role on user.userId=role.userIdRole where role.userRoleId=1 AND user.gmailId=:gmailIdsearch AND user.password=:passwordsearch";	
		Query query = session.createQuery(hql);			
		query.setString("gmailIdsearch", gmailId);
		query.setString("passwordsearch", password);
		List<User> userList=query.list();
		if(userList.size()>0)
		{
			User user=userList.get(0);			
			return user;				
		}		
		return null;
	}

	
	public List<User> getUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql=" FROM User user left outer join  Address address  on user.userId=address.userAddress join UserRoles role on user.userId=role.userIdRole where role.userRoleId=2";	
		Query query = session.createQuery(hql);
		List<User> userList=query.list();
		return userList;
	}

	
	public void deleteUser(long userId) {
		
		Session session = sessionFactory.openSession();
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            User user = (User) session.get(User.class, userId);            
    		
    		String hql=("from Orders where userIdPayment=:userId");		
    		Query query=session.createQuery(hql);
    		query.setParameter("userId", user);
    		List<Orders> orderList=query.list();
    		
    		
    		if(orderList.size()>0)
    		{
    			for(Orders orders:orderList)
    			{
    				String hql1=("delete OrderedItems where order=:orderId");		
    				Query query1=session.createQuery(hql1);
    				query1.setParameter("orderId", orders);
    				query1.executeUpdate();
    				
    			}
    		}
    		String hql8=("delete Orders where userIdPayment=:userId");		
    		Query query8=session.createQuery(hql8);
    		query8.setParameter("userId", user);
    		query8.executeUpdate();    		
    		
    		String hql2=("from ShoppingCart where userCart=: userId");    		
    		Query query2=session.createQuery(hql2);
    		query2.setParameter("userId", user);
    		List<ShoppingCart> cartList=query2.list();
    		System.out.println("cartlist: "+cartList);
    		if(cartList.size()>0)
    		{
    			
    				String hql3=("delete ShoppingCartItem where shoppingCart=:cartId");		
    				Query query3=session.createQuery(hql3);
    				query3.setParameter("cartId", cartList.get(0));
    				query3.executeUpdate();    				
    			
    		}
    		
    		String hql9=("delete ShoppingCart where userCart=: userId");    		
    		Query query9=session.createQuery(hql9);
    		query9.setParameter("userId", user);
    		query9.executeUpdate();    		
    		
    		String hql4=("delete Address where userAddress=:userId");		
    		Query query4=session.createQuery(hql4);
    		query4.setParameter("userId", user);
    		query4.executeUpdate();
    		
    		
    		String hql6=("delete  UserRoles  where userIdRole=:user and userRoleId=2");		
    		Query query6=session.createQuery(hql6);
    		query6.setParameter("user", user);
    		query6.executeUpdate();
    		
    		String hql7=("delete User where userId=:userId");		
    		Query query7=session.createQuery(hql7);
    		query7.setParameter("userId", userId);
    		query7.executeUpdate();
    		
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
