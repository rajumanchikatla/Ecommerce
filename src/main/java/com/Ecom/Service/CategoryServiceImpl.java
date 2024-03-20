package com.Ecom.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecom.Dao.CategoryDao;
import com.Ecom.Entity.Category;

@Service
public class CategoryServiceImpl {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category>getallcategory(){
		List<Category> categories = categoryDao.findAll();
		return categories;
	}
	
	public Optional<Category> getcategoryByid(long id){
		return categoryDao.findById(id);
	}
	
	public Category savecategory(Category category){
		return categoryDao.save(category);
	}
	
	public List<Category>saveallcategories(List<Category> category){
		return categoryDao.saveAll(category);
	}
	public void deletecatagory(long id){
		if(categoryDao.existsById(id)){
			categoryDao.deleteById(id);
		}else{
			throw new RuntimeException("Product not found");
		}
	}
	
	

}
