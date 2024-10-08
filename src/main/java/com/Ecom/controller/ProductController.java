 package com.Ecom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ecom.Entity.Product;
import com.Ecom.Exceptions.ProductNotFoundException;
import com.Ecom.Service.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	
	@GetMapping("/test")
	
	public String testingCode(){
		return "Test success";
		
		//changes not updated
	}
	
	
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
	public ResponseEntity<?> getproductByid(@PathVariable String id){
		try {
	        long productId = Long.parseLong(id);
			Optional<Product> productbyid = productServiceImpl.getbyid(productId);
			if(productbyid.isPresent()){
				Product product = productbyid.get();
				return ResponseEntity.ok(product);
			}else{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not availiable");
			}
		} catch(NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please enter valid number");
		}
	}
	//pagination
	
	@GetMapping("/products")
	public Page<Product>getEntities(@RequestParam(defaultValue = "0")int page,
									@RequestParam(defaultValue = "10")int size){
		return productServiceImpl.getEntities(page,size);
	}
	
	
	@PostMapping("/product")
	public ResponseEntity<String>createProduct( @RequestBody Product product){
		Product createProduct = productServiceImpl.saveproducts(product);
		if(createProduct!=null){
			 return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
	}
	
//	@PostMapping("/products")
//	public ResponseEntity<?> createproducts(@RequestBody List<Product> products){
//		List<Product> createProducts = productServiceImpl.saveallproducts(products);
//		if(createProducts!=null && !createProducts.isEmpty()){
//			 return ResponseEntity.status(HttpStatus.CREATED).body(createProducts);
//			  
//       } else {
//           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create products plz check....");
//       }
//	}
	
	@PutMapping("/product")
	public ResponseEntity<?>updateproduct(@RequestBody Product updateproduct)throws RuntimeException{
		try {
            Product result = productServiceImpl.updateProduct(updateproduct);
            return ResponseEntity.ok(result);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        	}
    	}
	
	 @PatchMapping("/product/{id}")
    public ResponseEntity<?> partialUpdateProduct(@PathVariable Long id, @RequestBody Product partialUpdateProduct) throws RuntimeException{
		 
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
	
	
	
	

