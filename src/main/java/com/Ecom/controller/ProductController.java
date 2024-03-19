package com.Ecom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecom.Exceptions.ProductNotFoundException;
import com.Ecom.Model.Product;
import com.Ecom.Service.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>>getproducts(){
		List<Product> allProducts = productServiceImpl.getAllproducts();
		if(allProducts!=null){
			return ResponseEntity.ok(allProducts);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getproductByid(@PathVariable long id){
		Optional<Product> productbyid = productServiceImpl.getbyid(id);
		if(productbyid.isPresent()){
			Product product = productbyid.get();
			return ResponseEntity.ok(product);
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not availiable");
		}
		
	}
	
	@PostMapping("/product")
	public ResponseEntity<String>createProduct(@RequestBody Product product){
		Product createProduct = productServiceImpl.saveproducts(product);
		if(createProduct!=null){
			 return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
	}
	
//	@PostMapping("/product")
//	public ResponseEntity<?> createproducts(@RequestBody List<Product> products){
//		List<Product> createProducts = productServiceImpl.saveallproducts(products);
//		if(createProducts!=null && !createProducts.isEmpty()){
//			 return ResponseEntity.status(HttpStatus.CREATED).body(createProducts);
//			  
//       } else {
//           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create products plz check....");
//       }
//	}
//	
	@PutMapping("/product")
	public ResponseEntity<?>updateproduct(@RequestBody Product updateproduct){
		try {
            Product result = productServiceImpl.updateProduct(updateproduct);
            return ResponseEntity.ok(result);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        	}
    	}
	
	 @PatchMapping("/product/{id}")
    public ResponseEntity<?> partialUpdateProduct(@PathVariable Long id, @RequestBody Product partialUpdateProduct) {
		 
        try {
        	partialUpdateProduct.setProductId(id);
            Product result = productServiceImpl.partialupdate(partialUpdateProduct);
            return ResponseEntity.ok(result);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

	 
	 @DeleteMapping("product/{id}")
	 public ResponseEntity<?>deletebyid(@PathVariable Long id){
		 try {
			 productServiceImpl.deletebyid(id);
	            return ResponseEntity.ok("Product deleted successfully");
	        } catch (ProductNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        }
	 }
	 
	 
	 
}
	
	
	
	

