package com.OnlineClothMart.Controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.OnlineClothMart.Model.Category;
import com.OnlineClothMart.Service.CategoryService;


@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/savecategory")
	public void saveCategory(@RequestBody Category category)throws IOException
	{
		this.categoryService.saveCategory(category);
		 
	}
	 @GetMapping("category")
	   public List<Category> getCategory()  {
		   
	      List<Category> category = this.categoryService.getCategory();
	     
	      return category;
	   }
	 @GetMapping("categoryById/{cid}")
	   public Category getCategoryById(@PathVariable("cid")int cid)
	   {
		  return this.categoryService.getCategoryById(cid);
	   }
	   
	   @GetMapping("deletecategory/{cid}")
	   public void deleteCategory(@PathVariable("cid")int cid)
	   {
		   this.categoryService.deleteCategory(cid);
	   }

}
