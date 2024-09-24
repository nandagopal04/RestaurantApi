package com.chs.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="customer")
@Setter
@Getter
@ToString
@EqualsAndHashCode
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
	@OneToMany(mappedBy = "customer", targetEntity = com.chs.entity.Order.class)
	@ToString.Exclude
	@JsonIgnore
	private List<Order> allOrdersofCustomer;
	
}
