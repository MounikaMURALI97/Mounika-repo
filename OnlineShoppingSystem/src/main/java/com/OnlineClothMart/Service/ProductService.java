package com.OnlineClothMart.Service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.Review;
import com.OnlineClothMart.dao.ProductDAO;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Transactional
	public List<Products> findProductByName(String productNameValue) {
		
		return this.productDAO.findProductByName(productNameValue);
	}


	@Transactional
	public List<Products> findProductByCategory(Integer cid) {
		
		return this.productDAO.findProductByCategory(cid);
	}


	@Transactional
	public List<Products> getProducts() {
		
		return this.productDAO.getProducts();
	}

	@Transactional
	public Products getProductById(int pid) {
		return this.productDAO.getProductById(pid);
		
	}

	@Transactional
	public void updateProductImage(byte[] bs,int pid) {
		 this.productDAO.updateProductImage(bs,pid);
		
	}

	@Transactional
	public void saveProduct(Products product) {
		 this.productDAO.saveProduct(product);
		
	}

	@Transactional
	public void deleteProduct(int pid) {
		this.productDAO.deleteProduct(pid);
	}

	@Transactional
	public Products getProductDetail(int id)
	{
		return this.productDAO.getProductDetail(id);
	}

	@Transactional
	public List<Review> getProductReview(int id) {
		
		return this.productDAO.getProductReview(id);
	}

	@Transactional
	public Review addReview(Review review, int pid, int userId) {
		
		return this.productDAO.addReview(review,pid,userId);
	}


	

}
