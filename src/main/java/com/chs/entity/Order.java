package com.chs.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Order implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899842507970901547L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;
	
	@ManyToMany
	@JoinTable(
			name="order_table",
			joinColumns = @JoinColumn(name="order_id"),
			inverseJoinColumns = @JoinColumn(name="item_id")
			)
	private List<Item> listOfItems;
	
	private Double price;

}
