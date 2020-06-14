package com.OnlineClothMart.Service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.OnlineClothMart.Model.Category;
import com.OnlineClothMart.dao.CategoryDAO;


@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;

	@Transactional
	public List<Category> getCategory() {
		return this.categoryDAO.getCategory();
	}

	@Transactional
	public void saveCategory(Category category) {
		this.categoryDAO.saveCategory(category);		
	}

	@Transactional
	public Category getCategoryById(int cid) {
		
		return this.categoryDAO.getCategoryById(cid);	
	}

	@Transactional
	public void deleteCategory(int cid) {
		this.categoryDAO.deleteCategory(cid);
		
	}


}
