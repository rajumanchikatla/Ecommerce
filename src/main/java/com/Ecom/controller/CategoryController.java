package com.Ecom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecom.Entity.Category;
import com.Ecom.Exceptions.ProductNotFoundException;
import com.Ecom.Service.CategoryServiceImpl;

@RestController
@RequestMapping("/api")
public class CategoryController {


	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@GetMapping("/category")
	public ResponseEntity<List<Category>>getallcategory(){
		List<Category> allcategory = categoryServiceImpl.getallcategory();
		if(allcategory!=null){
			return ResponseEntity.ok(allcategory);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		}
	}	

	@GetMapping("/category/{id}")
	public ResponseEntity<?> getProductbyid(@PathVariable long id){
		Optional<Category> categoryByid = categoryServiceImpl.getcategoryByid(id);
		if(categoryByid.isPresent()){
			Category category = categoryByid.get();
			return ResponseEntity.ok().body(category);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not avaliable");
		}
	}
	
	@PostMapping("/category")
	public ResponseEntity<String>addcategory(@RequestBody Category category){
		Category addcategory = categoryServiceImpl.savecategory(category);
		if(addcategory!=null){
			 return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
	}
	
/*	@PostMapping("/addcategories")
	public ResponseEntity<?>addcategories(@RequestBody List<Category> categories){
		List<Category> addcategories = categoryServiceImpl.saveallcategories(categories);
		if(!addcategories.isEmpty()){
			 return ResponseEntity.status(HttpStatus.CREATED).body(addcategories);
			  
	       } else {
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create categories plz check....");
	       }
	}*/
	
	@DeleteMapping("/category/{id}")
	 public ResponseEntity<?>deletebyid(@PathVariable Long id){
		 try {
			 categoryServiceImpl.deletecatagory(id);
	            return ResponseEntity.ok("Product deleted successfully");
	        } catch (ProductNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	 }
	
	

}
