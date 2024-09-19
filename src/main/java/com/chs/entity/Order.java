package com.chs.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899842507970901547L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customerId")
	
	private Customer customer;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="order_table",
			joinColumns = @JoinColumn(name="order_id"),
			inverseJoinColumns = @JoinColumn(name="item_id")
			)
	private List<Item> listOfItems;
	
	private Double price = priceCalc(listOfItems);
	
	private Double priceCalc(List<Item> allItems) {
		Double totalPrice = 0.0;
		if(allItems != null) {
			for(Item item : allItems) {
				totalPrice += item.getPrice();
			}
		}
		return totalPrice;
	}

}
