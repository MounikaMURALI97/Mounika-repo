package com.OnlineClothMart.dao;

import java.util.List;

import com.OnlineClothMart.Model.Category;

public interface CategoryDAO {

	List<Category> getCategory();

	void saveCategory(Category category);

	Category getCategoryById(int cid);

	void deleteCategory(int cid);

}
