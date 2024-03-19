package com.Ecom.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecom.Dao.ProductDao;
import com.Ecom.Entity.Product;

@Service
public class ProductServiceImpl {
	
	@Autowired
	private ProductDao productDao;

	public List<Product>getAllproducts(){
		List<Product> product = productDao.findAll();
		return product;	
	}
	
	public Optional<Product> getbyid(long id){
		return productDao.findById(id);
	}
	
	public Product saveproducts(Product product){
		return productDao.save(product);
	}
	
	public List<Product>saveallproducts(List<Product> product){
		return productDao.saveAll(product);
	}
	
	public Product updateProduct(Product product){
		if(productDao.existsById(product.getProductId())){
		Product updateproduct = productDao.save(product);
		return updateproduct;
		}else{
			throw new RuntimeException("product not found with id"+product.getProductId());
		}
	}
	
	public Product partialupdate(Product partialupdateproduct){
		Long productId = partialupdateproduct.getProductId();
		if(productDao.existsById(productId)){
			Product existingProduct = productDao.findById(productId).orElse(null);
			
			if(existingProduct!=null){
				if(partialupdateproduct.getProductName()!=null){
					existingProduct.setProductName(partialupdateproduct.getProductName());
				}
				if (partialupdateproduct.getProductDescription() != null) {
                    existingProduct.setProductDescription(partialupdateproduct.getProductDescription());
                }
                if (partialupdateproduct.getPrice() != null) {
                    existingProduct.setPrice(partialupdateproduct.getPrice());
                }
                
                Product partialupdated = productDao.save(existingProduct);
                return partialupdated;
			}
		}
		
		throw new RuntimeException("Product not found or unable to update");
	}
	
	public void deletebyid(long productId){
		if(productDao.existsById(productId)){
			productDao.deleteById(productId);
		}else{
			throw new RuntimeException("Product not found");
		}
	}
	
//	public Page<Product> getAllProducts(int pageNo, int pageSize) {
//        Pageable pageable = productDao.of(pageNo, pageSize);
//       
//    }

}
