package com.chs.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CustomerDto {

	private Long id;
	private String name;
	private Long phoneNumber;
	private String email;
	
}
