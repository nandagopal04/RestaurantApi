package com.chs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.chs.dto.OrderDto;
import com.chs.exception.InvalidEntityDetailsException;
import com.chs.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws InvalidEntityDetailsException{
		System.out.println(orderDto);
		OrderDto savedOrderDto = orderService.saveOrderDto(orderDto);
		return new ResponseEntity<OrderDto>(savedOrderDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderDto> findOrderById(@PathVariable Long id) throws InvalidEntityDetailsException{
		OrderDto orderDto = orderService.findOrderById(id);
		return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<OrderDto>> findAllOrders(){
		List<OrderDto> allOrderDtos = orderService.findAllOrderDtos();
		return new ResponseEntity<List<OrderDto>>(allOrderDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/customer/{id}")
	public ResponseEntity<List<OrderDto>> allOrdersOfCustomer(@PathVariable Long id) throws InvalidEntityDetailsException{
		List<OrderDto> allOrders = orderService.findAllOrdersOfCustomer(id);
		return new ResponseEntity<List<OrderDto>>(allOrders, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) throws InvalidEntityDetailsException{
		OrderDto updatedOrderDto = orderService.editOrderDto(orderDto);
		return new ResponseEntity<OrderDto>(updatedOrderDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<OrderDto> deleteOrder(@PathVariable Long id) throws InvalidEntityDetailsException{
		OrderDto orderDto = orderService.deleteOrderDto(id);
		return new ResponseEntity<OrderDto>(orderDto, HttpStatus.OK);
	}
	
}
