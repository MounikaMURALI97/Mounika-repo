package com.OnlineClothMart.dao;

import java.util.List;

import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.Review;

public interface ProductDAO {

	

	List<Products> findProductByName(String productNameValue);

	List<Products> findProductByCategory(Integer cid);

	List<Products> getProducts();

	Products getProductById(int pid);

	void updateProductImage(byte[] bs,int pid);

	void saveProduct(Products product);

	void deleteProduct(int pid);
	
	Products getProductDetail(int id);

	List<Review> getProductReview(int id);

	Review addReview(Review review, int pid, int userId);

	

}
