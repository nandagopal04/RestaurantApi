package com.chs.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class OrderDto {

	private Long id;
	private CustomerDto customerDto;
	private List<ItemDto> listOfItemsDto;
	private Double price;
	
}
