package com.OnlineClothMart.Controller;



import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.OnlineClothMart.Model.Products;
import com.OnlineClothMart.Model.Review;
import com.OnlineClothMart.Service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveproduct")
	public void saveProduct(@RequestBody Products product)throws IOException
	{
		this.productService.saveProduct(product);
		 
	}
	
	@PostMapping("/updateproductImage/{pid}")
	public void updateProductImage(@RequestParam("imageFile") MultipartFile file,@PathVariable("pid")int pid)throws IOException
	{
		
		this.productService.updateProductImage(file.getBytes(),pid);
		 
	}
	
	
	   @GetMapping("product/{productName}")
	   public List<Products> findProductByName(@PathVariable("productName") String productName)  {
		   
	      List<Products> products = this.productService.findProductByName(productName);
	     
	      return products;
	   }
	   
	   @GetMapping("category/{cid}")
	   public List<Products> findProductByCategory(@PathVariable("cid") Integer cid)  {
		   
	      List<Products> products = this.productService.findProductByCategory(cid);
	     
	      return products;
	   }
	   
	   @GetMapping("products")
	   public List<Products> getProducts()  {
		   
	      List<Products> products = this.productService.getProducts();
	     
	      return products;
	   }
	   
	   @GetMapping("productById/{pid}")
	   public Products getProductById(@PathVariable("pid")int pid)
	   {
		  return this.productService.getProductById(pid);
	   }
	   
	   @GetMapping("deleteproduct/{pid}")
	   public void deleteProduct(@PathVariable("pid")int pid)
	   {
		   this.productService.deleteProduct(pid);
	   }
	   
	   
	   @GetMapping("/products/{id}")
		public Products getProductDetail(@PathVariable("id") int id) {
		   Products products = this.productService.getProductDetail(id);
			return products;
		}
	   
	   @GetMapping("/review/{id}")
		public List<Review> getProductReview(@PathVariable("id") int id) {
		   return this.productService.getProductReview(id);
			
		}
	   
	   @PostMapping("/addReview/{userId}/pid/{pid}")
	  public Review addReview(@RequestBody Review review,@PathVariable("pid") int pid,@PathVariable("userId") int userId)
	  {
		   return this.productService.addReview(review,pid,userId);
	  }




}
