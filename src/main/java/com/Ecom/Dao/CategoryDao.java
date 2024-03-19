package com.Ecom.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecom.Entity.Category;

public interface CategoryDao extends JpaRepository<Category,Long> {

}
