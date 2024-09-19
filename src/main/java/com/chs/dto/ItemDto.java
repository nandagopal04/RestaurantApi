package com.chs.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ItemDto {

	private Long id;
	private String name;
	private Double price;
	
}
