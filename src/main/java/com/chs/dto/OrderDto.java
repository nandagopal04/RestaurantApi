package com.chs.dto;

import java.util.List;

import com.chs.entity.Customer;
import com.chs.entity.Item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderDto {

	private Long id;
	private Customer customer;
	private List<Item> listOfItems;
	private Double price;
	
}
