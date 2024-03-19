package com.Ecom.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecom.Model.Product;

public interface ProductDao extends JpaRepository<Product,Long>{

//	Pageable of(int pageNo, int pageSize);


	
	

}
