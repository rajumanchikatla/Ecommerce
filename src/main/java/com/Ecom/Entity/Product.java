package com.Ecom.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Product")
public class Product{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long productId;
	
	
	private String productName;
	
	
	private String productDescription;
	
	
//	@Column(name="category")
	@ManyToOne
	@JoinColumn(name = "id")
	@JsonIgnore
	private Category category;
	
	private Double price;

}
