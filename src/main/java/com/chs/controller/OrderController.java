package com.chs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chs.dto.OrderDto;
import com.chs.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder(OrderDto orderDto){
		OrderDto savedOrderDto = orderServiceImpl.saveOrderDto(orderDto);
		return new ResponseEntity<OrderDto>(savedOrderDto, HttpStatus.CREATED);
	}
	
}
