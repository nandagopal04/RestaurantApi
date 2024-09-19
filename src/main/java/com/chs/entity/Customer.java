package com.chs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 136024942770615349L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long phoneNumber;
	private String email;
	@OneToMany(mappedBy = "customer")
	@ToString.Exclude
	private List<Order> allOrdersofCustomer = new ArrayList<Order>();
	
}
