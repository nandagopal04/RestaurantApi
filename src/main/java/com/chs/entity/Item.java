package com.chs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5186892104010136955L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	@ManyToMany(mappedBy = "allItemsInOrder")
	@ToString.Exclude
	List<Order> OrderContainsItem = new ArrayList<Order>();
}
